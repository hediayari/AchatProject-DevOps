package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.rh.achat.controllers.CategorieProduitController;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryProductControllerTest {

    // Mockito Section
    @InjectMocks
    private CategorieProduitController categorieProduitController;
    @Mock
    private CategorieProduitServiceImpl categorieProduitService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCategorieProduit() {
        // Mockito Section
        List<CategorieProduit> categoryList = new ArrayList<>();

        // Create a CategorieProduita
        CategorieProduit category = new CategorieProduit();
        category.setIdCategorieProduit(1L);
        category.setCodeCategorie("Category001");
        category.setLibelleCategorie("Category001");
        categoryList.add(category);

        when(categorieProduitService.retrieveAllCategorieProduits()).thenReturn(categoryList);

        // JUnit Section
        List<CategorieProduit> result = categorieProduitController.getCategorieProduit();

        // Add your JUnit assertions here to check if the result matches your expectations
        assertEquals(1, result.size());
        CategorieProduit retrievedCategory = result.get(0);
        assertEquals(1L, retrievedCategory.getIdCategorieProduit());
        assertEquals("Category001", retrievedCategory.getCodeCategorie());
        assertEquals("Category001", retrievedCategory.getLibelleCategorie());
    }

    @Test
    public void testRetrieveCategorieProduit() {
        // Mockito Section
        Long categoryId = 1L;

        // Create a CategorieProduit
        CategorieProduit category = new CategorieProduit();
        category.setIdCategorieProduit(categoryId);
        category.setCodeCategorie("Category001");
        category.setLibelleCategorie("Category001");

        when(categorieProduitService.retrieveCategorieProduit(categoryId)).thenReturn(category);

        // JUnit Section
        CategorieProduit result = categorieProduitController.retrieveCategorieProduit(categoryId);

        // Add your JUnit assertions here to check if the result matches your expectations
        assertEquals(categoryId, result.getIdCategorieProduit());
        assertEquals("Category001", result.getCodeCategorie());
        assertEquals("Category001", result.getLibelleCategorie());
    }
}
