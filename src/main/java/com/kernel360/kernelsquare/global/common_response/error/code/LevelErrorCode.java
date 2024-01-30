package com.kernel360.kernelsquare.global.common_response.error.code;

import com.kernel360.kernelsquare.domain.level.service.LevelService;
import com.kernel360.kernelsquare.global.common_response.service.code.LevelServiceStatus;
import com.kernel360.kernelsquare.global.common_response.service.code.ServiceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum LevelErrorCode implements ErrorCode {
    LEVEL_NOT_FOUND(HttpStatus.NOT_FOUND, LevelServiceStatus.LEVEL_NOT_FOUND, "존재하지 않는 등급"),
    LEVEL_ALREADY_EXISTED(HttpStatus.CONFLICT, LevelServiceStatus.LEVEL_ALREADY_EXISTED, "이미 존재하는 등급");

    private final HttpStatus code;
    private final ServiceStatus serviceStatus;
    private final String msg;


    @Override
    public HttpStatus getStatus() {
        return code;
    }

    @Override
    public Integer getCode() {
        return serviceStatus.getServiceStatus();
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
