package com.kernelsquare.memberapi.domain.reservation_article.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.kernelsquare.domainmysql.domain.member.entity.Member;
import com.kernelsquare.domainmysql.domain.reservation_article.entity.ReservationArticle;
import com.kernelsquare.memberapi.domain.hashtag.dto.FindHashtagResponse;
import com.kernelsquare.memberapi.domain.image.utils.ImageUtils;
import com.kernelsquare.memberapi.domain.reservation.dto.FindReservationResponse;

public record FindReservationArticleResponse(
	Long articleId,
	Long memberId,
	String nickname,
	String memberImageUrl,
	Long level,
	String levelImageUrl,
	String title,
	String content,
	List<FindHashtagResponse> hashtags,
	List<FindReservationResponse> dateTimes,
	LocalDateTime createdDate,
	LocalDateTime modifiedDate
) {
	public static FindReservationArticleResponse of(
		Member member,
		ReservationArticle article,
		List<FindHashtagResponse> findHashtagResponses,
		List<FindReservationResponse> findReservationResponses) {
		return new FindReservationArticleResponse(
			article.getId(),
			member.getId(),
			member.getNickname(),
			ImageUtils.makeImageUrl(member.getImageUrl()),
			member.getLevel().getName(),
			ImageUtils.makeImageUrl(member.getLevel().getImageUrl()),
			article.getTitle(),
			article.getContent(),
			findHashtagResponses,
			findReservationResponses,
			article.getCreatedDate(),
			article.getModifiedDate()
		);

	}
}
