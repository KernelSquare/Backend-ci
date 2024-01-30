package com.kernel360.kernelsquare.global.common_response.service.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CommonServiceStatus implements ServiceStatus {
	//error
	DUPLICATE_DATA_EXIST(9000),
	VALIDATION_CHECK_FAIL(9001);

	private final Integer code;

	@Override
	public Integer getServiceStatus() {
		return code;
	}
}
