package io.bareun.sample.domain.file.service;

import io.bareun.base.common.dto.map.BaseMap;
import io.bareun.base.file.upload.ExcelUploadFile;
import io.bareun.base.file.writer.DefaultExcelWriter;
import io.bareun.sample.common.file.ExcelFileManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelFileService {

    private final ExcelFileManager excelFileManager;

    public ExcelUploadFile<BaseMap> upload(MultipartFile file) {
        // 현재 날짜
        LocalDate now = LocalDate.now();

        // 연/월/별 서브 디렉토리 구성
        String subDirectory = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();

        // 엑셀 파일 업로드 및 읽기 객체 반환
        return excelFileManager.uploadAndRead(file, subDirectory);
    }

    public ResponseEntity<?> download(String fileName) {
        // 샘플 리스트 가져오기 - TODO : DB 조회
        List<BaseMap> items = getItems();

        // 엑셀 쓰기 객체 생성
        DefaultExcelWriter writer = DefaultExcelWriter.of(items);

        // 엑셀 데이터에 맞는 헤더 생성 - 순서 중요
        writer.header("id", "아이디").header("name", "이름").header("age", "나이");

        // 다운로드할 파일명과 엑셀 쓰기 객체를 통해 다운로드 응답 반환
        return excelFileManager.download(fileName, writer);
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
