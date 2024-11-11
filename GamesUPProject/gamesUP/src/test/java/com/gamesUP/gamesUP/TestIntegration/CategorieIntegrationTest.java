package com.gamesUP.gamesUP.TestIntegration;

import com.gamesUP.gamesUP.model.Category;
import com.gamesUP.gamesUP.Repository.CategoryRepository;
import com.gamesUP.gamesUP.Service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional // Assure que les modifications dans la base de données sont annulées après chaque test
public class CategorieIntegrationTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    // Méthode d'initialisation pour ajouter des données avant les tests
    @BeforeEach
    public void setup() {
        // Efface la base de données et ajoute des catégories de test avant chaque test
        categoryRepository.deleteAll();
        Category category1 = new Category(1L, "Action");
        Category category2 = new Category(2L, "Adventure");
        categoryRepository.save(category1);
        categoryRepository.save(category2);
    }

    @Test
    public void testGetAllCategories() {
        // Test pour récupérer toutes les catégories
        List<Category> categories = categoryService.getAllCategories();
        assertEquals(2, categories.size(), "Le nombre de catégories doit être 2");
    }

    @Test
    public void testGetCategoryById() {
        // Test pour récupérer une catégorie par son ID
        Optional<Category> category = categoryService.getCategoryById(1L);
        assertTrue(category.isPresent(), "La catégorie avec l'ID 1 doit être présente");
        assertEquals("Action", category.get().getType(), "Le type de la catégorie doit être 'Action'");
    }

    @Test
    public void testAddCategory() {
        // Test pour ajouter une nouvelle catégorie
        Category newCategory = new Category(3L, "RPG");
        Category savedCategory = categoryService.addCategory(newCategory);

        assertNotNull(savedCategory, "La catégorie ajoutée ne doit pas être nulle");
        assertEquals("RPG", savedCategory.getType(), "Le type de la catégorie ajoutée doit être 'RPG'");
    }

    @Test
    public void testUpdateCategory() {
        // Test pour mettre à jour une catégorie existante
        Category categoryToUpdate = new Category(1L, "FPS");
        Category updatedCategory = categoryService.updateCategory(1L, categoryToUpdate);

        assertNotNull(updatedCategory, "La catégorie mise à jour ne doit pas être nulle");
        assertEquals("FPS", updatedCategory.getType(), "Le type de la catégorie mise à jour doit être 'FPS'");
    }

    @Test
    public void testDeleteCategory() {
        // Test pour supprimer une catégorie par son ID
        categoryService.deleteCategory(2L);
        Optional<Category> deletedCategory = categoryService.getCategoryById(2L);
        assertFalse(deletedCategory.isPresent(), "La catégorie avec l'ID 2 doit avoir été supprimée");
    }
}
