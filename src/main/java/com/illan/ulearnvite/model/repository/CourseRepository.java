package com.illan.ulearnvite.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.illan.ulearnvite.model.entity.Course;


@Repository
public interface CourseRepository  extends JpaRepository<Course,Long>{

    Course findCourseById(Long id);
    
    Page<Course> findAll(Pageable pageable);
    

}
