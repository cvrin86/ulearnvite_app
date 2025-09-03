package com.illan.ulearnvite.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.illan.ulearnvite.model.entity.Answer;
import com.illan.ulearnvite.model.entity.Question;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {

    List<Answer> findByQuestion(Question question);

}
