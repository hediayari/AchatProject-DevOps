package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplTest {

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @Test
    public void testRetrieveAllFournisseurs() {
        // Given
        Fournisseur f1 = new Fournisseur();
        Fournisseur f2 = new Fournisseur();
        given(fournisseurRepository.findAll()).willReturn(Arrays.asList(f1, f2));

        // When
        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        then(fournisseurRepository).should().findAll();
    }

    @Test
    public void testAddFournisseur() {
        // Given
        Fournisseur f = new Fournisseur();
        given(fournisseurRepository.save(f)).willReturn(f);

        // When
        Fournisseur saved = fournisseurService.addFournisseur(f);

        // Then
        assertNotNull(saved);
        then(fournisseurRepository).should().save(f);
    }


    @Test
    public void testDeleteFournisseur() {
        // Given
        Long id = 1L;

        // When
        fournisseurService.deleteFournisseur(id);

        // Then
        then(fournisseurRepository).should().deleteById(id);
    }

    @Test
    public void testRetrieveFournisseur() {
        // Given
        Long id = 1L;
        Fournisseur f = new Fournisseur();
        given(fournisseurRepository.findById(id)).willReturn(Optional.of(f));

        // When
        Fournisseur found = fournisseurService.retrieveFournisseur(id);

        // Then
        assertNotNull(found);
        then(fournisseurRepository).should().findById(id);
    }


}
