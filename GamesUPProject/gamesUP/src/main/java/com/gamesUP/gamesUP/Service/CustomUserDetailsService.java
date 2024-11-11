package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Constructor for dependency injection
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Retrieve user from your repository
        User appUser = userRepository.findByNom(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le nom : " + username));

        // Create Spring Security User object with details from appUser
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(appUser.getNom());
        builder.password(appUser.getPassword()); // Use getPassword()
        builder.roles(appUser.getRoles().stream().map(role -> role.getName()).toArray(String[]::new));

        return builder.build();
    }

    // Ajouter la méthode pour récupérer l'ID de l'utilisateur par son nom
    public Long getUserIdByUsername(String username) {
        // Retrieve user from your repository
        User appUser = userRepository.findByNom(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le nom : " + username));

        // Retourner l'ID de l'utilisateur
        return appUser.getId();
    }
}
