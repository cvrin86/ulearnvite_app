package com.illan.ulearnvite.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;


public class LoginDTO {
	
	  @NotBlank(message = "Le nom est obligatoire")
	    private String username;

	    @NotBlank(message = "Le mot de passe est obligatoire")
	    private String password;

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

		
	   
	    
	    

}
