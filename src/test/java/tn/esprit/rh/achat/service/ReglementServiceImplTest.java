package tn.esprit.rh.achat.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
 class ReglementServiceImplTest {

    @Mock
    private ReglementRepository reglementRepository;

    @InjectMocks
    private ReglementServiceImpl reglementService;

    @BeforeEach
    void setUp() {
        // Define the behavior of the mock repository
        List<Reglement> reglements = new ArrayList<>();
        reglements.add(new Reglement(/* Initialize with appropriate values */));
        reglements.add(new Reglement(/* Initialize with appropriate values */));

        Mockito.when(reglementRepository.findAll()).thenReturn(reglements);
    }

    @Test
     void testRetrieveAllReglements() {
        List<Reglement> retrievedReglements = reglementService.retrieveAllReglements();

        // Assert that the method returns the list of Reglements as expected
        assertEquals(2, retrievedReglements.size()); // Adjust the expected size accordingly
    }
}
