package io.bareun.sample.domain.file.controller;

import io.bareun.base.common.dto.map.BaseMap;
import io.bareun.base.common.dto.response.ApiResponse;
import io.bareun.base.file.upload.ExcelUploadFile;
import io.bareun.sample.domain.file.service.ExcelFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files/excel")
public class ExcelFileController {

    private final ExcelFileService excelFileService;

    @PostMapping("/upload")
    public ApiResponse<ExcelUploadFile<BaseMap>> uploadFile(@RequestParam MultipartFile file) {
        return ApiResponse.success(excelFileService.upload(file));
    }

    @GetMapping("/download")
    public ResponseEntity<?> download() {
        return excelFileService.download();
    }

}
