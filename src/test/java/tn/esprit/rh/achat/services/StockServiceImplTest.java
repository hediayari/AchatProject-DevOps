package tn.esprit.rh.achat.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)

public class StockServiceImplTest {

    @Mock private StockRepository stockRepository;
    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    public void testRetrieveAllStocks() {
        // Create a sample list of stocks
        Stock stock1 = new Stock("Stock 1", 100, 10);
        Stock stock2 = new Stock("Stock 2", 200, 20);
        List<Stock> stockList = new ArrayList<>();
        stockList.add(stock1);
        stockList.add(stock2);

        // Mock the behavior of the stockRepository
        when(stockRepository.findAll()).thenReturn(stockList);

        // Call the service method
        List<Stock> retrievedStocks = stockService.retrieveAllStocks();

        // Verify that the service method returns the expected list of stocks
        assert retrievedStocks.equals(stockList);
        // assertThat(retrievedStocks).isEqualTo(stockList);
    }
    @Test
    public void testAddStock() {
        // Create a sample stock
        Stock stock = new Stock("New Stock", 50, 5);

        // Mock the behavior of the stockRepository save method
        when(stockRepository.save(stock)).thenReturn(stock);

        // Call the service method
        Stock addedStock = stockService.addStock(stock);

        // Verify that the service method returns the added stock
        assert addedStock.equals(stock);
    }

}
