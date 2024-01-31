package com.kernel360.kernelsquare.domain.coffeechat.kafka;

import com.kernel360.kernelsquare.domain.coffeechat.dto.ChatMessage;
import com.kernel360.kernelsquare.global.common_response.error.code.CoffeeChatErrorCode;
import com.kernel360.kernelsquare.global.common_response.error.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestKafkaProducer {
    @Autowired
    private KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public void produce(ChatMessage message) {
        switch (message.getType()) {
            case ENTER -> message.setMessage(message.getSender()+"님이 입장하였습니다.");
            case LEAVE -> message.setMessage(message.getSender()+"님이 퇴장하였습니다.");
            case TALK -> {}
            default -> throw new BusinessException(CoffeeChatErrorCode.MESSAGE_TYPE_NOT_VALID);
        }
        kafkaTemplate.send("test_" + message.getRoomKey(), message);
    }
}
