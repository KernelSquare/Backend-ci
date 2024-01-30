package com.kernel360.kernelsquare.domain.hashtag.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kernel360.kernelsquare.domain.reservation_article.entity.ReservationArticle;
import com.kernel360.kernelsquare.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Hashtag")
@Getter
@Table(name = "hashtag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hashtag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false ,name = "content", columnDefinition = "varchar(30)")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_article_id", columnDefinition = "bigint", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private ReservationArticle reservationArticle;

    @Builder
    public Hashtag(Long id, String content, ReservationArticle reservationArticle) {
        this.id = id;
        this.content = content;
        this.reservationArticle = reservationArticle;
    }

}
