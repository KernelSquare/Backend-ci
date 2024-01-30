package com.kernel360.kernelsquare.global.common_response.service.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ImageServiceStatus implements ServiceStatus {
    //error
    IMAGE_IS_EMPTY(2400),
    IMAGE_UPLOAD_FAILED(2401),

    //success
    IMAGE_UPLOAD_COMPLETED(2440),
    IMAGE_DELETED(2441);

    private final Integer code;

    @Override
    public Integer getServiceStatus() {
        return code;
    }
}
