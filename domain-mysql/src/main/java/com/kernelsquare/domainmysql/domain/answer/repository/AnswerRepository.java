package com.kernelsquare.domainmysql.domain.answer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kernelsquare.domainmysql.domain.answer.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	@Query("SELECT a FROM Answer a WHERE a.question.id = :questionId ORDER BY a.createdDate DESC")
	List<Answer> findAnswersByQuestionIdSortedByCreationDate(@Param("questionId") Long questionId);

	@Modifying
	@Query("UPDATE Answer a SET a.voteCount = a.voteCount + 1 WHERE a.id = :answerId")
	void upVoteAnswer(@Param("answerId") Long answerId);

	@Modifying
	@Query("UPDATE Answer a SET a.voteCount = a.voteCount - 1 WHERE a.id = :answerId")
	void downVoteAnswer(@Param("answerId") Long answerId);
}
