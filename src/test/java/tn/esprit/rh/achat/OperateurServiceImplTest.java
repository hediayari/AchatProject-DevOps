import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
