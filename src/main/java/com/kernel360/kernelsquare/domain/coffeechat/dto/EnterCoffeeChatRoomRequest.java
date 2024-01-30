package com.kernel360.kernelsquare.domain.coffeechat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record EnterCoffeeChatRoomRequest(
    @NotNull(message = "회원 ID를 입력해 주세요.")
    Long memberId,
    @NotNull(message = "방 ID를 입력해 주세요.")
    Long roomId,
    @NotBlank(message = "예약창 제목을 입력해 주세요.")
    String articleTitle
) {
}
