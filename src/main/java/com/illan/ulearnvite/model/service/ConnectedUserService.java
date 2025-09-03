package com.illan.ulearnvite.model.service;


import com.illan.ulearnvite.model.entity.User;
import com.illan.ulearnvite.model.repository.UserRepository;
import com.illan.ulearnvite.security.ConnectedUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



//Charger un utilisateur pour l'autentification
@Service
public class ConnectedUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return new ConnectedUser(user); // on cree un objet connected User
    }
}
