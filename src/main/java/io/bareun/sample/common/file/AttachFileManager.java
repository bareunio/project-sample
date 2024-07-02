package io.bareun.sample.common.file;

import io.bareun.base.file.download.AttachDownloadFile;
import io.bareun.base.file.manager.FileManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AttachFileManager implements FileManager {

    @Value("${base.file.diretory}")
    private String fileDirectory;

    @Override
    public String getDirectory() {
        return fileDirectory;
    }

    public ResponseEntity<?> download(String fileName) {
        return download(AttachDownloadFile.builder()
                .downloadFileName(fileName)
                .storedFilePath(getFullPath(fileName))
                .build());
    }
}
