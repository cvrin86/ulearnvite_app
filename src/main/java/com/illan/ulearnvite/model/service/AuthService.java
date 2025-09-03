package com.illan.ulearnvite.model.service;

import com.illan.ulearnvite.dto.LoginDTO;
import com.illan.ulearnvite.dto.RegisterDTO;
import com.illan.ulearnvite.model.entity.User;
import com.illan.ulearnvite.security.ConnectedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ConnectedUserService connectedUserService;



  public String authenticateUser(LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(),
                    loginDTO.getPassword()
                )
            );

            // Ici on stocke manuellement l'authentification dans le contexte
            SecurityContextHolder.getContext().setAuthentication(authentication);

            ConnectedUser connectedUser = (ConnectedUser) authentication.getPrincipal();
            return connectedUser.getUsername();

        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Identifiants invalides");
        }
    }
}
