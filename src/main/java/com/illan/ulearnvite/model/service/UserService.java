package com.illan.ulearnvite.model.service;

import org.springframework.stereotype.Service;

import com.illan.ulearnvite.dto.RegisterDTO;
import com.illan.ulearnvite.enums.ERole;
import com.illan.ulearnvite.model.entity.Role;
import com.illan.ulearnvite.model.entity.User;
import com.illan.ulearnvite.model.repository.RoleRepository;
import com.illan.ulearnvite.model.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Récupérer un utilisateur par ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Récupérer un utilisateur par email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    
    public User registerUser(RegisterDTO registerDTO) {
        // Vérification de la correspondance des mots de passe
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Les mots de passe ne correspondent pas.");
        }

        // Vérifie si l'email existe déjà
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new IllegalArgumentException("Cet email est déjà utilisé.");
        }

        // Vérifie si le nom d'utilisateur existe déjà
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new IllegalArgumentException("Ce nom d'utilisateur est déjà pris.");
        }

        // Création et configuration de l'utilisateur
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword())); // Encodage du mot de passe
        user.setEnabled(true);

        // Affectation du rôle par défaut
        Role defaultRole = roleRepository.findByName(ERole.ROLE_ADMIN);
        if (defaultRole == null) {
            throw new RuntimeException("Le rôle USER est introuvable.");
        }
        user.setRoles(List.of(defaultRole));

        // Option : image de profil par défaut
         if (user.getProfilePicture() == null || user.getProfilePicture().isBlank()) {
             user.setProfilePicture("/images/default-profile.png");
         }

        // Sauvegarde de l'utilisateur
        return userRepository.save(user);
    }


    
    

    // Mettre à jour un utilisateur
    public User updateUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Cet email est déjà utilisé.");
        }
        // Encoder le mot de passe si modifié
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    // Supprimer un utilisateur
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

