package com.illan.ulearnvite.model.entity;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Entity
@ToString(exclude="courses")
@Data
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Le nom de categorie ne doit pas etre vide")
	@Column(nullable = false,length = 50)
	private String name;
	
	@NotBlank(message="Le nom de ce champ ne doit pas etre vide")
	// @Column(nullable = false,length = 80)
	private String description;
	
    // Relation OneToMany avec Course, la clé étrangère est gérée dans Course
	@OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
	private List<Course> courses;





}
