package com.illan.ulearnvite.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.illan.ulearnvite.model.entity.Lesson;
import com.illan.ulearnvite.model.entity.Quiz;

@Repository
public interface QuizRepository  extends JpaRepository <Quiz,Long> {


Quiz findByLesson(Lesson lesson);
}
