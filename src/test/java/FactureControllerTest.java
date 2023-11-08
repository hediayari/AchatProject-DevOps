import static org.mockito.Mockito.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.rh.achat.controllers.FactureRestController;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.services.IFactureService;

import java.util.HashSet;

public class FactureControllerTest {

    @InjectMocks
    private FactureRestController factureController;

    @Mock
    private IFactureService factureService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(factureController).build();
    }

    @Test
    public void testAddFacture() throws Exception {
        // Create a sample Facture object with associated entities
        Facture f = new Facture();
        f.setMontantRemise(100.0f);
        f.setMontantFacture(500.0f);
        // Set up the associations, e.g., detailsFacture, fournisseur, reglements
        f.setDetailsFacture(new HashSet<>());
        f.setFournisseur(new Fournisseur()); // Create a new Fournisseur or mock it
        f.setReglements(new HashSet<>());

        when(factureService.addFacture(any(Facture.class))).thenReturn(f);

        mockMvc.perform(MockMvcRequestBuilders.post("/add-facture")
                        .content(new ObjectMapper().writeValueAsString(f))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
