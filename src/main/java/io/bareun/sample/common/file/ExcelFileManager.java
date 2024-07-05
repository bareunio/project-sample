package io.bareun.sample.common.file;

import io.bareun.base.common.dto.map.BaseMap;
import io.bareun.base.file.download.ExcelDownloadFile;
import io.bareun.base.file.manager.FileManager;
import io.bareun.base.file.upload.AttachUploadFile;
import io.bareun.base.file.upload.ExcelUploadFile;
import io.bareun.base.file.util.ExcelFileUtils;
import io.bareun.base.file.writer.ExcelWriter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class ExcelFileManager implements FileManager {

    @Value("${base.file.diretory}")
    private String fileDirectory;

    @Setter
    private String subDirectory;

    @Override
    public String getDirectory() {
        return fileDirectory + "/excel";
    }

    @Override
    public String getSubDirectory() {
        return subDirectory;
    }

    public ExcelUploadFile<BaseMap> uploadAndRead(MultipartFile file) {
        return uploadAndRead(file, "");
    }

    public ExcelUploadFile<BaseMap> uploadAndRead(MultipartFile file, String subDirectory) {
        setSubDirectory(subDirectory);

        // 첨부파일 업로드
        AttachUploadFile attachUploadFile = upload(file);

        // 엑셀 업로드 파일로 전환
        ExcelUploadFile<BaseMap> excelUploadFile = ExcelUploadFile.of(attachUploadFile);

        // 엑셀 파일 읽기
        List<BaseMap> list = ExcelFileUtils.read(file, BaseMap.class);

        // 읽은 엑셀 데이터 저장
        excelUploadFile.addAll(list);

        // 엑셀 업로드 객체 반환
        return excelUploadFile;
    }

    public ResponseEntity<?> download(String downloadFileName, ExcelWriter excelWriter) {
        // 엑셀 데이터 쓰기
        byte[] metaData = ExcelFileUtils.write(excelWriter);

        // 엑셀 다운로드 객체 생성
        ExcelDownloadFile downloadFile = ExcelDownloadFile.builder()
                .downloadFileName(downloadFileName)
                .metaData(metaData)
                .build();

        // 생성한 엑셀 다운로드
        return download(downloadFile);
    }
}
