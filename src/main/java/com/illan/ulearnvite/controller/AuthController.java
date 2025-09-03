package com.illan.ulearnvite.controller;

import com.illan.ulearnvite.dto.LoginDTO;
import com.illan.ulearnvite.dto.RegisterDTO;
import com.illan.ulearnvite.model.entity.User;
import com.illan.ulearnvite.model.service.AuthService;
import com.illan.ulearnvite.model.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthService authService;

// // Affiche la page de connexion (GET)
@GetMapping("/login")
public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                            Model model) {
    model.addAttribute("loginDTO", new LoginDTO());
    model.addAttribute("content", "auth/login");

    if (error != null) {
        model.addAttribute("error", "Nom d'utilisateur ou mot de passe invalide.");
    }

    return "base";
}



    



    // Affiche la page d'inscription
  @GetMapping("/register")
  public String registerFormPage(Model model) {
	  
	  model.addAttribute("registerDTO", new RegisterDTO());
	  model.addAttribute("content", "auth/register");
	  
	  return "base";
  }

  // Traitement du formulaire d'inscription
  @PostMapping("/register")
  public String processRegistration(@Valid @ModelAttribute("registerDTO") RegisterDTO registerDTO,
                                    BindingResult result, Model model) {

      if (result.hasErrors()) {
    	  model.addAttribute("registerDTO", registerDTO);
    	  model.addAttribute("content", "auth/register");
          return "base";
      }

      try {
          userService.registerUser(registerDTO);
          return("redirect:/auth/login");
//          model.addAttribute("success", "Inscription réussie !");
      } catch (IllegalArgumentException e) {
          result.rejectValue("email", null, e.getMessage());
          return("redirect:/auth/register");
      }

    
  }
    
    
    
    
    

//    // Déconnexion
//    @PostMapping("/logout")
//    public String logout() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            SecurityContextHolder.clearContext(); // Efface la session
//        }
//        return "redirect:/"; // Redirige vers la page d'accueil après déconnexion
//    }
}
