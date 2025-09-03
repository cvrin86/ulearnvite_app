package com.illan.ulearnvite.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.illan.ulearnvite.model.entity.Course;
import com.illan.ulearnvite.model.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public Page<Course> getAllCourses(int page,int size){
Pageable pageable = PageRequest.of(page,size);
        return courseRepository.findAll(pageable);
    }


    public Course getCourseById(Long id){

        return courseRepository.findById(id).orElse(null);
    }


    public List<Course> getPopularCourses(){
        // return courseRepository.findAll(PageRequest.of(0,6)).getContent();
        return courseRepository.findAll();
    }

}
