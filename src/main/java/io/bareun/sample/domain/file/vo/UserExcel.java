package io.bareun.sample.domain.file.vo;

import io.bareun.base.file.annotation.ExcelHeader;
import lombok.Data;

@Data
public class UserExcel {

    @ExcelHeader("아이디")
    private Long id;

    @ExcelHeader("이름")
    private String name;

    @ExcelHeader("나이")
    private int age;
}
