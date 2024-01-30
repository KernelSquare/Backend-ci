package com.kernel360.kernelsquare.domain.coffeechat.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private MessageType type;

    private String roomKey;

    private String sender;

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}
