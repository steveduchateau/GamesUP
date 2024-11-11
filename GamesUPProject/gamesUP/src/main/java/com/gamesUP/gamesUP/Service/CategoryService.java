package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Category;
import com.gamesUP.gamesUP.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Méthode pour récupérer toutes les catégories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Méthode pour récupérer une catégorie par son ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Méthode pour ajouter une nouvelle catégorie
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Méthode pour mettre à jour une catégorie existante
    public Category updateCategory(Long id, Category categoryDetails) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setType(categoryDetails.getType());
            return categoryRepository.save(category);
        } else {
            // Gestion des erreurs ou catégorie non trouvée (optionnel)
            return null;
        }
    }

    // Méthode pour supprimer une catégorie par son ID
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
