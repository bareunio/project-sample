package io.bareun.sample.domain.file.service;

import io.bareun.base.common.dto.map.BaseMap;
import io.bareun.base.file.upload.ExcelUploadFile;
import io.bareun.base.file.writer.DefaultExcelWriter;
import io.bareun.sample.common.file.ExcelFileManager;
import io.bareun.sample.domain.file.vo.UserExcel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelFileService {

    private final ExcelFileManager excelFileManager;

    public ExcelUploadFile<BaseMap> upload(MultipartFile file) {
        return excelFileManager.uploadAndRead(file);
    }

    public ResponseEntity<?> download() {
        return excelFileManager.download(DefaultExcelWriter.of(getItems(), UserExcel.class));
    }

    private List<BaseMap> getItems() {
        return Arrays.asList(
                new BaseMap("id", 1L).set("name", "Donesha").set("age", 82),
                new BaseMap("id", 2L).set("name", "Esteven").set("age", 22),
                new BaseMap("id", 3L).set("name", "Jeannie").set("age", 35),
                new BaseMap("id", 4L).set("name", "Shivani").set("age", 28),
                new BaseMap("id", 5L).set("name", "Mikenna").set("age", 61),
                new BaseMap("id", 6L).set("name", "Jillianne").set("age", 45),
                new BaseMap("id", 7L).set("name", "JavaJester").set("age", 33),
                new BaseMap("id", 8L).set("name", "CinnamonBun56").set("age", 19),
                new BaseMap("id", 9L).set("name", "MoonlightShadow").set("age", 57),
                new BaseMap("id", 10L).set("name", "PixieDust").set("age", 48),
                new BaseMap("id", 11L).set("name", "Yee").set("age", 30)
        );
    }
}
