package io.bareun.sample.domain.file.controller;

import io.bareun.base.common.dto.response.ApiResponse;
import io.bareun.base.file.upload.AttachUploadFile;
import io.bareun.sample.domain.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ApiResponse<AttachUploadFile> uploadFile(@RequestParam MultipartFile file) {
        return ApiResponse.success(fileService.upload(file));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id, @RequestParam String fileName) {
        return fileService.download(fileName);
    }
}
