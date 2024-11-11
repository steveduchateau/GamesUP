package com.gamesUP.gamesUP.Controller;

import com.gamesUP.gamesUP.model.Category;
import com.gamesUP.gamesUP.Service.CategoryService;
import com.gamesUP.gamesUP.controller.CategoryController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController; // Change ici

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category(1L, "Board Game");
    }

    @Test
    void testGetAllCategories() {
        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(category));

        var response = categoryController.getAllCategories(); // Correction ici

        assertEquals(1, response.size());
        assertEquals("Board Game", response.get(0).getType());
    }

    @SuppressWarnings("null")
    @Test
    void testGetCategoryById_Found() {
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(category));

        ResponseEntity<Category> response = categoryController.getCategoryById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Board Game", response.getBody().getType());
    }

    @Test
    void testGetCategoryById_NotFound() {
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Category> response = categoryController.getCategoryById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @SuppressWarnings("null")
    @Test
    void testAddCategory() {
        when(categoryService.addCategory(category)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.addCategory(category);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Board Game", response.getBody().getType());
    }

    @SuppressWarnings("null")
    @Test
    void testUpdateCategory() {
        when(categoryService.updateCategory(1L, category)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.updateCategory(1L, category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Board Game", response.getBody().getType());
    }

    @Test
    void testDeleteCategory() {
        doNothing().when(categoryService).deleteCategory(1L);

        ResponseEntity<Void> response = categoryController.deleteCategory(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoryService, times(1)).deleteCategory(1L);
    }
}
