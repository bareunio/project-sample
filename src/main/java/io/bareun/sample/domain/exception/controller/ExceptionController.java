package io.bareun.sample.domain.exception.controller;

import io.bareun.base.exception.BusinessException;
import io.bareun.base.exception.code.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/required")
    public void required() {
        throw new BusinessException(ErrorCode.REQUIRED, "아이디");
    }

    @GetMapping("/validate")
    public void validate() {
        throw new BusinessException(ErrorCode.VALIDATE, "이메일");
    }

    @GetMapping("/message")
    public void message() {
        throw new BusinessException("업무 에러");
    }

    @GetMapping("/code")
    public void code() {
        throw new BusinessException(ErrorCode.BAD_REQUEST);
    }
}
