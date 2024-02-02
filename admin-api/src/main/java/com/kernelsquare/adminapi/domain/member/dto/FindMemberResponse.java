package com.kernelsquare.adminapi.domain.member.dto;

import com.kernelsquare.adminapi.domain.image.utils.ImageUtils;
import com.kernelsquare.domainmysql.domain.member.entity.Member;

import lombok.Builder;

@Builder
public record FindMemberResponse(
	Long memberId,
	String nickname,
	Long experience,
	String introduction,
	String imageUrl,
	Long level
) {

	public static FindMemberResponse from(Member member) {
		return FindMemberResponse
			.builder()
			.memberId(member.getId())
			.nickname(member.getNickname())
			.experience(member.getExperience())
			.introduction(member.getIntroduction())
			.imageUrl(ImageUtils.makeImageUrl(member.getImageUrl()))
			.level(member.getLevel().getName())
			.build();
	}
}


