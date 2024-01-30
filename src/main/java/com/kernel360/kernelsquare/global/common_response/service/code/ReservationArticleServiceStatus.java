package com.kernel360.kernelsquare.global.common_response.service.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReservationArticleServiceStatus implements ServiceStatus {
    //error
    RESERVATION_ARTICLE_NOT_FOUND(3100),
    PAGE_NOT_FOUND(3101),

    //success
    RESERVATION_ARTICLE_CREATED(3140),
    RESERVATION_ARTICLE_FOUND(3141),
    RESERVATION_ARTICLE_ALL_FOUND(3142),
    RESERVATION_ARTICLE_DELETED(3143);



    private final Integer code;

    @Override
    public Integer getServiceStatus() { return code;}

}
