package io.bareun.sample.domain.exception.controller;

import io.bareun.base.common.dto.response.ApiResponse;
import io.bareun.base.exception.BusinessException;
import io.bareun.base.exception.code.BaseErrorCode;
import io.bareun.sample.common.code.CustomErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/required")
    public ApiResponse<?> required() {
        throw new BusinessException(BaseErrorCode.REQUIRED, "아이디");
    }

    @GetMapping("/validate")
    public ApiResponse<?> validate() {
        throw new BusinessException(BaseErrorCode.VALIDATE, "이메일");
    }

    @GetMapping("/message")
    public ApiResponse<?> message() {
        throw new BusinessException("업무 에러");
    }

    @GetMapping("/code")
    public ApiResponse<?> code() {
        throw new BusinessException(BaseErrorCode.BAD_REQUEST);
    }

    @GetMapping("/custom/code")
    public ApiResponse<?> customCode() {
        throw new BusinessException(CustomErrorCode.CUSUTOM_NOT_FOUND);
    }
}
