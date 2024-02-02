package com.kernelsquare.memberapi.domain.coffeechat.controller;

import com.kernelsquare.memberapi.config.TestWebSocketConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.kernelsquare.core.common_response.error.code.CoffeeChatErrorCode;
import com.kernelsquare.core.common_response.error.exception.BusinessException;
import com.kernelsquare.memberapi.domain.coffeechat.dto.ChatMessage;

@Controller
class TestMessageController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/test/message")
	public void messageHandler(ChatMessage message) {

		switch (message.getType()) {
			case ENTER -> message.setMessage(message.getSender() + "님이 입장하였습니다.");
			case TALK -> {}
			case CODE -> {}
			case LEAVE -> message.setMessage(message.getSender() + "님이 퇴장하였습니다.");
			case EXPIRE -> {}
			default -> throw new BusinessException(CoffeeChatErrorCode.MESSAGE_TYPE_NOT_VALID);
		}

		messagingTemplate.convertAndSend("/topic/test/room/" + message.getRoomKey(), message);
	}
}