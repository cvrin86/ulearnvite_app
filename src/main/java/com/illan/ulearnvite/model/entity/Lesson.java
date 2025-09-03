package com.illan.ulearnvite.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Lesson {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String title;
	
	@NotBlank
	@Column(nullable = false)
	private String content;
	
	@ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

	@OneToOne(mappedBy = "lesson")
private VideoFile video; 

@OneToOne(mappedBy = "lesson")
private Quiz quiz;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public Section getSection() {
	return section;
}

public void setSection(Section section) {
	this.section = section;
}

public VideoFile getVideo() {
	return video;
}

public void setVideo(VideoFile video) {
	this.video = video;
}

public Quiz getQuiz() {
	return quiz;
}

public void setQuiz(Quiz quiz) {
	this.quiz = quiz;
}








}
