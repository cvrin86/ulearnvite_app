package com.illan.ulearnvite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.illan.ulearnvite.model.service.ConnectedUserService;


//gerer l'authentification

// authentification manuelle sans utiliser le formLogin de Spring qui est dans configuration
@Configuration
public class SecurityConfig {
    @Autowired
    ConnectedUserService connectedUserService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(connectedUserService).passwordEncoder(this.passwordEncoder());
        return authenticationManagerBuilder.build();
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth ->

        auth.requestMatchers("/admin/**").hasRole("ADMIN") // routes
                                                           // commencant par
                                                           // / admin
                                                           // accessibles aux
                                                           // utilisateurs
                                                           // ADMIN
               // 🔹 Seuls les connectés peuvent commenter
                .anyRequest().permitAll()).csrf(csrf -> csrf.disable())

                .formLogin(form -> form
                .loginPage("/auth/login")
                .failureUrl("/auth/login?error=true")
                .defaultSuccessUrl("/", false)
                .permitAll()
            )
            

                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true) // la sessions utilisateur n est plus valise
                        .deleteCookies("JSESSIONID") // supprime le cookie de session du client // JSESSIONID: le nom du
                                                     // cookie sur l'ordinateur du user
                        .permitAll())

                .sessionManagement(session -> session.maximumSessions(1)); // limite le nombre de session par
                                                                           // utilisateur

        return httpSecurity.build();
    }



    

}

