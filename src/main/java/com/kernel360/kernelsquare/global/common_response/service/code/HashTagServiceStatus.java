package com.kernel360.kernelsquare.global.common_response.service.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum HashTagServiceStatus implements ServiceStatus {
    //error
    HASHTAG_NOT_FOUND(3301),

    //success
    HASHTAG_ALL_FOUND(3340),
    HASHTAG_DELETED(3341);

    private final Integer code;

    @Override
    public Integer getServiceStatus() { return code; }

}
