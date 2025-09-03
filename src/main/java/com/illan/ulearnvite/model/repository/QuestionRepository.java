package com.illan.ulearnvite.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.illan.ulearnvite.model.entity.Question;
import com.illan.ulearnvite.model.entity.Quiz;

@Repository
public interface QuestionRepository extends JpaRepository <Question,Long> {

    List<Question> findByQuiz(Quiz quiz);

}
