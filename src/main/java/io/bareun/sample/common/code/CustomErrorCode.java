package io.bareun.sample.common.code;

import io.bareun.base.exception.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode {

    /**
     * 10000 ~ 11000
     */
    CUSUTOM_NOT_FOUND(10001, "커스텀 찾지 못하였습니다.")
    ;

    private final int code;
    private final String message;

}
