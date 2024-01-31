package com.kernel360.kernelsquare.domain.coffeechat.kafka;

import com.kernel360.kernelsquare.domain.coffeechat.dto.ChatMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class TestKafkaConsumer {
    private CountDownLatch latch = new CountDownLatch(1);
    private List<ChatMessage> eventRepo = new ArrayList<>();

    @KafkaListener(topicPattern = "test_.*", groupId = "testGroup")
    public void consume(ChatMessage message) {
        eventRepo.add(message);
        latch.countDown();
    }

    public List<ChatMessage> getEventRepo() {
        return eventRepo;
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
