//package tn.esprit.rh.achat.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import tn.esprit.rh.achat.entities.Reglement;
//import tn.esprit.rh.achat.repositories.ReglementRepository;
//import tn.esprit.rh.achat.services.ReglementServiceImpl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
// class ReglementServiceImplTest {
//
//    @Mock
//    private ReglementRepository reglementRepository;
//
//    @InjectMocks
//    private ReglementServiceImpl reglementService;
//
//    @BeforeEach
//    void setUp() {
//        List<Reglement> reglements = new ArrayList<>();
//        Reglement reglement1 = new Reglement();
//        reglement1.setIdReglement(1L);
//        reglement1.setMontantPaye(100.0f);
//        reglement1.setMontantRestant(50.0f);
//        reglement1.setPayee(false);
//        reglement1.setDateReglement(new Date());
//
//        Reglement reglement2 = new Reglement();
//        reglement2.setIdReglement(2L);
//        reglement2.setMontantPaye(200.0f);
//        reglement2.setMontantRestant(75.0f);
//        reglement2.setPayee(true);
//        reglement2.setDateReglement(new Date());
//
//        reglements.add(reglement1);
//        reglements.add(reglement2);
//
//        Mockito.when(reglementRepository.findAll()).thenReturn(reglements);
//
//    }
//
//    @Test
//     void testRetrieveAllReglements() {
//        List<Reglement> retrievedReglements = reglementService.retrieveAllReglements();
//
//        assertEquals(2, retrievedReglements.size());
//    }
//}
