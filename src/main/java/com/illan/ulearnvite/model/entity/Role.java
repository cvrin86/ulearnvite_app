package com.illan.ulearnvite.model.entity;


import java.util.List;

import com.illan.ulearnvite.enums.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotBlank(message = "Le nom du rôle ne peut pas être vide")
	@Column(nullable = false,unique=true,length = 20)
    @Enumerated(EnumType.STRING)
	 private ERole name;
	
    @NotBlank(message = "La description du rôle ne peut pas être vide")
	@Size(max=150, message="La description ne doit pas depasser 150 caracteres")
	@Column(nullable = false,length = 150)
private String description;

	public Long getId() {
		return id;
	}

	public ERole getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
    
//    @OneToMany(mappedBy = "role")
//    private List<User> users;
    
    
	
	

}
