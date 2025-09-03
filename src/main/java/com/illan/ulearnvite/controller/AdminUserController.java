package com.illan.ulearnvite.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.illan.ulearnvite.dto.RegisterDTO;
import com.illan.ulearnvite.dto.UserDTO;
import com.illan.ulearnvite.model.entity.User;
import com.illan.ulearnvite.model.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String listUsers(Model model) {
		
		 // Récupère tous les utilisateurs et les convertit en UserDTO
        List<UserDTO> usersDTO = userService.getAllUsers().stream()
            .map(this::convertToDTO)  // On utilise la méthode de conversion pour chaque User
            .collect(Collectors.toList());
	    model.addAttribute("users", usersDTO); // Passe la liste à la vue

	    model.addAttribute("content", "admin/users/list"); // Fragment spécifique à afficher dans base.html
	    return "base"; // Affiche la vue Thymeleaf "base.html"
	    
	    
	}
	// Méthode de conversion pour chaque utilisateur
	private UserDTO convertToDTO(User user) {
		return new UserDTO(user);  // Conversion d'un User en UserDTO
	}
	
	


    // 6. Formulaire d'ajout
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/users/add";
    }

//    // 7. Enregistrement d'un nouvel utilisateur
    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") RegisterDTO registerDTO) {
        userService.registerUser(registerDTO);
        return "redirect:/users/admin";
    }

    // 8. Modifier un utilisateur
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/users/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
//        user.setId(id);
//        userService.updateUser(id, user);
        return "redirect:/users/admin";
    }

    // 9. Supprimer un utilisateur
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users/admin";
    }
		
		
		
	}
	


