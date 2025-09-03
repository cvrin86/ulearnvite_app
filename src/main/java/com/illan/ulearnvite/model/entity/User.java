package com.illan.ulearnvite.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Le nom d'utilisateur est obligatoire")
	@Size(min = 2, max = 30, message = "Le nom de l'utilisateur ne doit pas dépasser 30 caractères")
	@Column(nullable = false, unique = true, length = 30)
	private String username;

	@NotBlank(message = "L'adresse email est obligatoire")
	@Email(message = "L'adresse email doit être valide")
	@Column(nullable = false, unique = true, length = 100)
	private String email;

	@NotBlank(message = "Le mot de passe est obligatoire")
	@Size(min = 12, message = "Le mot de passe doit contenir au moins 12 caractères")
	@Column(nullable = false)
	private String password;

	@Size(max = 255, message = "L'URL de la photo de profil ne peut pas dépasser 255 caractères")
	private String profilePicture;

	@Size(max = 500, message = "La biographie ne peut pas dépasser 500 caractères")
	private String bio;

	private Boolean isEnabled = true;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<Role>();

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Score> scores;

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
}
