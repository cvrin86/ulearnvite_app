package com.illan.ulearnvite.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.illan.ulearnvite.model.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {

}
