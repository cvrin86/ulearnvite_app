package com.illan.ulearnvite.dto;

import java.util.ArrayList;
import java.util.List;

import com.illan.ulearnvite.model.entity.Role;
import com.illan.ulearnvite.model.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class UserDTO {

    private Long id;

    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
    @Size(min = 2, max = 30, message = "Le nom d'utilisateur doit être compris entre 2 et 30 caractères")
    private String username;

    @NotBlank(message = "L'email ne peut pas être vide")
    @Email(message = "L'email doit être valide")
    private String email;

    @Size(max = 255, message = "L'URL de la photo de profil ne peut pas dépasser 255 caractères")
    private String profilePicture;

    @Size(max = 500, message = "La biographie ne peut pas dépasser 500 caractères")
    private String bio;
    
    @NotEmpty(message = "Le rôle ne peut pas être vide")
    private List<String> roleNames = new ArrayList<>();

    
    
    // Constructeur pour convertir un User en UserDTO

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.profilePicture = user.getProfilePicture();
        this.bio = user.getBio();
        if(user.getRoles()!=null) {
        	
        	for(Role role:user.getRoles()) {
        		this.roleNames.add(role.getName().name());
        	}
        }
    }

    
    


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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



	public List<String> getRoleNames() {
		return roleNames;
	}



	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}
    
    
    


    
    
}
