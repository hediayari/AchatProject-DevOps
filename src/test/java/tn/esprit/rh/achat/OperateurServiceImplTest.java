package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OperateurServiceImplTest {

    @Mock
    OperateurRepository operateurRepository;

    @InjectMocks
    OperateurServiceImpl operateurService;

    @Test
    public void testRetrieveOperateur() {
        Operateur mockOperateur = new Operateur();
        mockOperateur.setIdOperateur(1L);
        mockOperateur.setNom("TestNom");
        mockOperateur.setPrenom("TestPrenom");
        mockOperateur.setPassword("TestPassword");

        Mockito.when(operateurRepository.findById(1L)).thenReturn(java.util.Optional.of(mockOperateur));

        Operateur retrievedOperateur = operateurService.retrieveOperateur(1L);

        Assertions.assertNotNull(retrievedOperateur);
        Assertions.assertEquals(mockOperateur.getIdOperateur(), retrievedOperateur.getIdOperateur());
        Assertions.assertEquals(mockOperateur.getNom(), retrievedOperateur.getNom());
    }
    @Test
    public void testAddOperateur() {
        Operateur newOperateur = new Operateur();
        newOperateur.setNom("NewNom");
        newOperateur.setPrenom("NewPrenom");
        newOperateur.setPassword("NewPassword");


        Mockito.when(operateurRepository.save(newOperateur)).thenReturn(newOperateur);


        Operateur addedOperateur = operateurService.addOperateur(newOperateur);

        // Assertions
        Assertions.assertNotNull(addedOperateur);
        Assertions.assertEquals(newOperateur.getNom(), addedOperateur.getNom());
        Assertions.assertEquals(newOperateur.getPrenom(), addedOperateur.getPrenom());
        Assertions.assertEquals(newOperateur.getPassword(), addedOperateur.getPassword());
    }

}
