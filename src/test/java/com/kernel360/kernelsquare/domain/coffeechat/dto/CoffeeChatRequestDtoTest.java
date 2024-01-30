package com.kernel360.kernelsquare.domain.coffeechat.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CoffeeChat 도메인 요청 Dto 단위 테스트")
class CoffeeChatRequestDtoTest {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Test
    @DisplayName("채팅방 생성 요청 검증 테스트")
    void validateCreateCoffeeChatRoomRequest() {
        CreateCoffeeChatRoomRequest createCoffeeChatRoomRequest = CreateCoffeeChatRoomRequest.builder()
            .roomName("")
            .build();

        Set<ConstraintViolation<CreateCoffeeChatRoomRequest>> violations = validator.validate(createCoffeeChatRoomRequest);
        Set<String> msgList = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());

        //then
        assertThat(msgList).isEqualTo(Set.of("방 이름을 입력해 주세요."));
    }

    @Test
    @DisplayName("채팅방 입장 요청 검증 테스트")
    void validateEnterCoffeeChatRoomRequest() {
        EnterCoffeeChatRoomRequest enterCoffeeChatRoomRequest = EnterCoffeeChatRoomRequest.builder()
            .articleTitle("")
            .build();

        Set<ConstraintViolation<EnterCoffeeChatRoomRequest>> violations = validator.validate(enterCoffeeChatRoomRequest);
        Set<String> msgList = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());

        //then
        assertThat(msgList).isEqualTo(Set.of("회원 ID를 입력해 주세요.", "방 ID를 입력해 주세요.", "예약창 제목을 입력해 주세요."));
    }
}