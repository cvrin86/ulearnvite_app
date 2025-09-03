package com.illan.ulearnvite.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "section",fetch = FetchType.EAGER)
private List<Quiz> quizzes;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Course getCourse() {
		return course;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public List<Quiz> getQuizzes() {
		return quizzes;
	}
    
    
    
    
    
}
