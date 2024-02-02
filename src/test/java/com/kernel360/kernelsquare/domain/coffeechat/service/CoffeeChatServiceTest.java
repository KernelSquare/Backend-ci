package com.kernel360.kernelsquare.domain.coffeechat.service;

import com.kernel360.kernelsquare.domain.coffeechat.dto.*;
import com.kernel360.kernelsquare.domain.coffeechat.entity.ChatRoom;
import com.kernel360.kernelsquare.domain.coffeechat.entity.MongoChatMessage;
import com.kernel360.kernelsquare.domain.coffeechat.entity.MongoMessageType;
import com.kernel360.kernelsquare.domain.coffeechat.repository.CoffeeChatRepository;
import com.kernel360.kernelsquare.domain.coffeechat.repository.MongoChatMessageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("커피챗 서비스 단위 테스트")
@ExtendWith(MockitoExtension.class)
class CoffeeChatServiceTest {
    @InjectMocks
    private CoffeeChatService coffeeChatService;
    @Mock
    private CoffeeChatRepository coffeeChatRepository;
    @Mock
    private MongoChatMessageRepository mongoChatMessageRepository;

    @Test
    @DisplayName("채팅방 입장 테스트")
    void testEnterCoffeeChatRoom() {
        //given
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            "1", null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_MENTOR")));

        given(securityContext.getAuthentication()).willReturn(authentication);

        ChatRoom chatRoom = ChatRoom.builder()
            .id(Long.valueOf(authentication.getName()))
            .roomKey("asd")
            .expirationTime(LocalDateTime.now().plusMinutes(30))
            .build();

        EnterCoffeeChatRoomRequest enterCoffeeChatRoomRequest = EnterCoffeeChatRoomRequest.builder()
            .roomId(1L)
            .memberId(Long.valueOf(authentication.getName()))
            .articleTitle("불꽃남자의 예절 주입방")
            .build();

        given(coffeeChatRepository.findById(anyLong())).willReturn(Optional.of(chatRoom));

        //when
        EnterCoffeeChatRoomResponse response = coffeeChatService.enterCoffeeChatRoom(enterCoffeeChatRoomRequest);

        //then
        assertThat(response).isNotNull();
        assertThat(response.articleTitle()).isEqualTo(chatRoom.getRoomName());
        assertThat(response.roomKey()).isEqualTo(chatRoom.getRoomKey());
        assertThat(response.active()).isTrue();

        //verify
        verify(coffeeChatRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("채팅방 나가기 테스트")
    void testLeaveCoffeeChatRoom() {
        //given
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            "1", null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_MENTOR")));

        given(securityContext.getAuthentication()).willReturn(authentication);

        String roomKey = "asdf";

        ChatRoom chatRoom = ChatRoom.builder()
            .id(1L)
            .roomKey(roomKey)
            .build();

        String articleTitle = "홍박사님의 명강";

        chatRoom.activateRoom(articleTitle);

        given(coffeeChatRepository.findByRoomKey(anyString())).willReturn(Optional.of(chatRoom));

        //when
        coffeeChatService.leaveCoffeeChatRoom(roomKey);

        //then
        assertThat(chatRoom).isNotNull();
        assertThat(chatRoom.getRoomName()).isEqualTo(articleTitle);
        assertThat(chatRoom.getRoomKey()).isEqualTo(roomKey);
        assertThat(chatRoom.getActive()).isTrue();

        //verify
        verify(coffeeChatRepository, times(1)).findByRoomKey(anyString());
    }

    @Test
    @DisplayName("채팅 내역 조회 테스트")
    void testFindChatHistory() {
        //given
        MongoChatMessage mongoChatMessage = MongoChatMessage.builder()
            .roomKey("key")
            .type(MongoMessageType.TALK)
            .message("hi")
            .sender("에키드나")
            .sendTime(LocalDateTime.now())
            .build();

        given(mongoChatMessageRepository.findAllByRoomKey(anyString())).willReturn(List.of(mongoChatMessage));

        //when
        FindChatHistoryResponse findChatHistoryResponse = coffeeChatService.findChatHistory(mongoChatMessage.getRoomKey());

        //then
        assertThat(findChatHistoryResponse.chatHistory()).isNotNull();
        assertThat(findChatHistoryResponse.chatHistory().get(0).getMessage()).isEqualTo(mongoChatMessage.getMessage());
        assertThat(findChatHistoryResponse.chatHistory().get(0).getSender()).isEqualTo(mongoChatMessage.getSender());
        assertThat(findChatHistoryResponse.chatHistory().get(0).getRoomKey()).isEqualTo(mongoChatMessage.getRoomKey());
        assertThat(findChatHistoryResponse.chatHistory().get(0).getType()).isEqualTo(mongoChatMessage.getType());
        assertThat(findChatHistoryResponse.chatHistory().get(0).getSendTime()).isEqualTo(mongoChatMessage.getSendTime());

        //verify
        verify(mongoChatMessageRepository, times(1)).findAllByRoomKey(anyString());
    }
}