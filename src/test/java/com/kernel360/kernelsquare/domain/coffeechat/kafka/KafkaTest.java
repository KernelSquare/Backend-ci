package com.kernel360.kernelsquare.domain.coffeechat.kafka;

import com.kernel360.kernelsquare.domain.coffeechat.dto.ChatMessage;
import com.kernel360.kernelsquare.domain.coffeechat.dto.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Kafka 단위 테스트")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@TestPropertySource(properties = {"spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}"})
public class KafkaTest {
//    @Autowired
//    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private TestKafkaProducer producer;

    @Autowired
    private TestKafkaConsumer consumer;

//    @BeforeEach
//    public void setUp() {
//        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.consumerProps("testGroup", "true", embeddedKafkaBroker));
//        consumer.setConsumerConfigs(configs);
//    }

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

        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);


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
