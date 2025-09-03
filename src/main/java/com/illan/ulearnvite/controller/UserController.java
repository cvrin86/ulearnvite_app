package com.illan.ulearnvite.controller;

import com.illan.ulearnvite.model.entity.User;
import com.illan.ulearnvite.model.service.UserService;
import com.illan.ulearnvite.security.ConnectedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ PARTIE UTILISATEUR (profil, modification, suppression)

    // 1. Voir son profil
    @GetMapping("/profil")
    public String getProfile(Model model, @AuthenticationPrincipal ConnectedUser connectedUser) {
        model.addAttribute("user", connectedUser);
        return "user/profil";
    }

    // 2. Formulaire pour modifier ses infos
    @GetMapping("/profil/modifier")
    public String showEditProfileForm(Model model, @AuthenticationPrincipal ConnectedUser connectedUser) {
        model.addAttribute("user", connectedUser);
        return "user/edit-profil";
    }

    // 3. Soumettre les modifications
    @PostMapping("/profil/modifier")
    public String updateProfile(@ModelAttribute("user") User updatedUser,
                                @AuthenticationPrincipal ConnectedUser connectedUser) {
//        userService.updateUser(connectedUser.getUser().getId(), updatedUser);
        return "redirect:/users/profil";
    }

    // 4. Supprimer son compte
    @PostMapping("/profil/delete")
    public String deleteAccount(@AuthenticationPrincipal ConnectedUser connectedUser) {
//        userService.deleteUser(connectedUser.getUser().getId());
        return "redirect:/deconnexion";
    }

}

