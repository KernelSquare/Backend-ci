package com.kernelsquare.core.common_response.error.code;

import org.springframework.http.HttpStatus;

import com.kernelsquare.core.common_response.service.code.CoffeeChatServiceStatus;
import com.kernelsquare.core.common_response.service.code.ServiceStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CoffeeChatErrorCode implements ErrorCode {
	MESSAGE_TYPE_NOT_VALID(HttpStatus.BAD_REQUEST, CoffeeChatServiceStatus.MESSAGE_TYPE_NOT_VALID, "유효하지 않는 메시지 타입"),
	COFFEE_CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, CoffeeChatServiceStatus.COFFEE_CHAT_ROOM_NOT_FOUND, "존재하지 않는 채팅방"),
	COFFEE_CHAT_ROOM_NOT_ACTIVE(HttpStatus.BAD_REQUEST, CoffeeChatServiceStatus.COFFEE_CHAT_ROOM_NOT_ACTIVE,
		"비활성화 상태의 채팅방"),
	MENTOR_MISMATCH(HttpStatus.BAD_REQUEST, CoffeeChatServiceStatus.MENTOR_MISMATCH, "해당 예약창을 만든 멘토와 불일치"),
	MEMBER_MISMATCH(HttpStatus.BAD_REQUEST, CoffeeChatServiceStatus.MEMBER_MISMATCH, "해당 예약 시간의 멤버와 불일치"),
	AUTHORITY_NOT_VALID(HttpStatus.BAD_REQUEST, CoffeeChatServiceStatus.AUTHORITY_NOT_VALID, "유효하지 않는 권한"),
	MESSAGE_DELIVERY_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, CoffeeChatServiceStatus.MESSAGE_DELIVERY_FAILED,
		"토픽으로 메시지 전달 실패"),
	COFFEE_CHAT_ROOM_EXPIRED(HttpStatus.BAD_REQUEST, CoffeeChatServiceStatus.COFFEE_CHAT_ROOM_EXPIRED,"만료된 채팅방");

	private final HttpStatus httpStatus;
	private final ServiceStatus serviceStatus;
	private final String msg;

	@Override
	public HttpStatus getStatus() {
		return httpStatus;
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