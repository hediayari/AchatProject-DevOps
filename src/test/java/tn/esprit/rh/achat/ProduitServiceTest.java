package tn.esprit.rh.achat;



import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

public class ProduitServiceTest {

    @InjectMocks
    private ProduitServiceImpl produitService;

    @Mock
    private ProduitRepository produitRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllProduits() {
        Produit produit1 = new Produit();
        produit1.setIdProduit(1L);

        Produit produit2 = new Produit();
        produit2.setIdProduit(2L);

        Mockito.when(produitRepository.findAll()).thenReturn(Arrays.asList(produit1, produit2));

        List<Produit> result = produitService.retrieveAllProduits();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testAddProduit() {
        Produit produit = new Produit();
        produit.setIdProduit(1L);
        produit.setCodeProduit("PROD001");

        Mockito.when(produitRepository.save(ArgumentMatchers.any(Produit.class))).thenReturn(produit);

        Produit result = produitService.addProduit(produit);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(produit.getCodeProduit(), result.getCodeProduit());
    }

    @Test
    public void testRetrieveProduit() {
        Produit produit = new Produit();
        produit.setIdProduit(1L);

        Mockito.when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));

        Produit result = produitService.retrieveProduit(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(produit.getIdProduit(), result.getIdProduit());
    }


}
