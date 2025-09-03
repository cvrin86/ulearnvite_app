package com.illan.ulearnvite.model.entity;


import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Score {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Byte scoreValue;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedAt;

	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="quiz_id",nullable = false)
	private Quiz quiz;
}
