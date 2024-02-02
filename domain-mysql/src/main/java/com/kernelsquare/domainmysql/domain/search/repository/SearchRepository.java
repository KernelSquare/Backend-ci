package com.kernelsquare.domainmysql.domain.search.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.kernelsquare.domainmysql.domain.question.entity.Question;

@Repository
public interface SearchRepository {
	Page<Question> searchQuestionsByKeyword(Pageable pageable, String keyword);
}
