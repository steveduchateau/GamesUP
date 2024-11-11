package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Role;
import com.gamesUP.gamesUP.Repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Méthode pour initialiser les rôles par défaut (client, admin)
    @PostConstruct
    public void initRoles() {
        // Vérifie si les rôles "client" et "admin" existent déjà, sinon les créer
        if (!roleRepository.findByName("client").isPresent()) {
            roleRepository.save(new Role("client"));
        }
        if (!roleRepository.findByName("admin").isPresent()) {
            roleRepository.save(new Role("admin"));
        }
    }

    // Méthode pour récupérer tous les rôles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Méthode pour récupérer un rôle par son ID
    public Optional<Role> getRoleById(int id) {
        return roleRepository.findById(id);
    }

    // Méthode pour ajouter un nouveau rôle
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    // Méthode pour mettre à jour un rôle
    public Role updateRole(int id, Role roleDetails) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            role.setName(roleDetails.getName());
            return roleRepository.save(role);
        }
        return null;
    }

    // Méthode pour supprimer un rôle
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }

    // Méthode pour récupérer un rôle par son nom
    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
