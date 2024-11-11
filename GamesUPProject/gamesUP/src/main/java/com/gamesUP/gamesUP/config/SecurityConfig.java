package com.gamesUP.gamesUP.config;

import com.gamesUP.gamesUP.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    // Constructeur pour l'injection de dépendances
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    // Configuration de sécurité normale (prod, dev, etc.)
    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Désactive la protection CSRF (pour les tests uniquement, à reconfigurer pour la prod)
            .authorizeRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()  // Accès libre aux routes d'authentification
                .requestMatchers("/public/**").permitAll()  // Accès libre aux ressources publiques
                .requestMatchers("/python-data").permitAll()  // Accès libre à /python-data
                .requestMatchers("/getRecommendation").permitAll()  // Accès libre à /getRecommendation
                .requestMatchers("/api/users").permitAll()  // Accès libre à /api/users pour l'inscription
                .requestMatchers("/user/**").authenticated()  // Protection des routes /user/**, nécessite une authentification
                .requestMatchers("/admin/**").hasRole("ADMIN")  // Routes /admin/** accessibles seulement aux utilisateurs ayant le rôle ADMIN
                .requestMatchers("/client/**").hasRole("CLIENT")  // Routes /client/** accessibles seulement aux utilisateurs ayant le rôle CLIENT
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login")  // Page de login personnalisée
                .permitAll()  // Autorise tous les utilisateurs à accéder à la page de login
                .defaultSuccessUrl("/user/dashboard", true)  // Redirige vers le dashboard après une connexion réussie
            )
            .logout(logout -> logout
                .permitAll()  // Permet à tous les utilisateurs de se déconnecter
            )
            .exceptionHandling(handling -> handling
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")));  // Redirection vers la page de login en cas de tentative d'accès non authentifiée

        return http.build();
    }

    // Si le profil "test" est actif, désactiver la sécurité
    @SuppressWarnings("deprecation")
    @Profile("test")
    @Bean
    public SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Désactive la protection CSRF pour les tests
                .authorizeRequests(authorizeRequests -> authorizeRequests
                                .anyRequest().permitAll()  // Permet l'accès à toutes les requêtes sans authentification
                )
                .formLogin(login -> login.disable())  // Désactive la page de login pour les tests
                .logout(logout -> logout.disable());  // Désactive la fonctionnalité de déconnexion pour les tests

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Utilise BCrypt pour l'encodage des mots de passe
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;  // Utilise CustomUserDetailsService pour charger les détails de l'utilisateur
    }
}
