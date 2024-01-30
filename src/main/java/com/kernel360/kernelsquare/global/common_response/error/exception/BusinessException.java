package com.kernel360.kernelsquare.global.common_response.error.exception;

import com.kernel360.kernelsquare.global.common_response.error.code.ErrorCode;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
	private final transient ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
