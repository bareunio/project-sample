package io.bareun.sample.domain.file.service;

import io.bareun.base.file.upload.AttachUploadFile;
import io.bareun.sample.common.file.AttachFileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {

    private final AttachFileManager uploadFileManager;

    public AttachUploadFile upload(MultipartFile file) {
        return uploadFileManager.upload(file);
    }

    public ResponseEntity<?> download(String fileName) {
        return uploadFileManager.download(fileName);
    }
}
