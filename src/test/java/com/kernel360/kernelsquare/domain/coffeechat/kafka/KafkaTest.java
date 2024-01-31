package com.kernel360.kernelsquare.domain.coffeechat.kafka;

import com.kernel360.kernelsquare.domain.coffeechat.dto.ChatMessage;
import com.kernel360.kernelsquare.domain.coffeechat.dto.MessageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Kafka 단위 테스트")
@SpringBootTest
public class KafkaTest {
    @Autowired
    private TestKafkaProducer producer;

    @Autowired
    private TestKafkaConsumer consumer;

    @DisplayName("메시지 송수신 테스트")
    @Test
    void TestSendAndReceive() throws InterruptedException {
        //given
        ChatMessage sendMessage = ChatMessage.builder()
            .message("hi")
            .roomKey("key")
            .type(MessageType.TALK)
            .sender("홍박사")
            .build();

        //when
        producer.produce(sendMessage);
        consumer.getLatch().await(30, TimeUnit.SECONDS);


        //then
        assertThat(consumer.getLatch().getCount()).isEqualTo(0L);

        List<ChatMessage> receiveMessages = consumer.getEventRepo();

        assertThat(receiveMessages.size()).isNotZero();
        assertThat(receiveMessages.get(0).getMessage()).isEqualTo(sendMessage.getMessage());
        assertThat(receiveMessages.get(0).getRoomKey()).isEqualTo(sendMessage.getRoomKey());
        assertThat(receiveMessages.get(0).getType()).isEqualTo(sendMessage.getType());
        assertThat(receiveMessages.get(0).getSender()).isEqualTo(sendMessage.getSender());
    }
}
