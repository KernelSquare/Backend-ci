package com.kernelsquare.domainmysql.domain.base;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	@CreatedDate
	@Column(nullable = false, name = "created_date", columnDefinition = "datetime", updatable = false)
	private LocalDateTime createdDate;

	@LastModifiedDate
	@Column(name = "modified_date", columnDefinition = "datetime")
	private LocalDateTime modifiedDate;
}
