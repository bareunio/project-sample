package io.bareun.sample.common.file;

import io.bareun.base.common.dto.map.BaseMap;
import io.bareun.base.file.download.ExcelDownloadFile;
import io.bareun.base.file.manager.FileManager;
import io.bareun.base.file.upload.ExcelUploadFile;
import io.bareun.base.file.util.ExcelFileUtils;
import io.bareun.base.file.writer.ExcelWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ExcelFileManager implements FileManager {

    @Value("${base.file.diretory}")
    private String fileDirectory;

    @Override
    public String getDirectory() {
        return fileDirectory + "/excel";
    }

    public ExcelUploadFile<BaseMap> uploadAndRead(MultipartFile file) {
        ExcelUploadFile<BaseMap> upload = ExcelUploadFile.of(upload(file));

        return upload.addAll(ExcelFileUtils.read(file, BaseMap.class));
    }

    public <T> ResponseEntity<?> download(ExcelWriter<T> excelWriter) {
        return download(ExcelDownloadFile.builder()
                .downloadFileName("샘플_다운로드.xlsx")
                .metaData(ExcelFileUtils.write(excelWriter))
                .build());
    }
}
