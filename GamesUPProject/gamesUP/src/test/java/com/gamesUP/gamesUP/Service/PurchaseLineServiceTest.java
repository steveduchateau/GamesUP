package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.PurchaseLine;
import com.gamesUP.gamesUP.Repository.PurchaseLineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseLineServiceTest {

    @InjectMocks
    private PurchaseLineService purchaseLineService;

    @Mock
    private PurchaseLineRepository purchaseLineRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPurchaseLines() {
        PurchaseLine line1 = new PurchaseLine(1, 101, 202, 29.99);
        PurchaseLine line2 = new PurchaseLine(2, 102, 203, 49.99);
        when(purchaseLineRepository.findAll()).thenReturn(Arrays.asList(line1, line2));

        assertEquals(2, purchaseLineService.getAllPurchaseLines().size());
    }

    @Test
    void testGetPurchaseLineById() {
        PurchaseLine line = new PurchaseLine(1, 101, 202, 29.99);
        when(purchaseLineRepository.findById(1)).thenReturn(Optional.of(line));

        Optional<PurchaseLine> result = purchaseLineService.getPurchaseLineById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    void testAddPurchaseLine() {
        PurchaseLine line = new PurchaseLine(3, 103, 204, 19.99);
        when(purchaseLineRepository.save(any(PurchaseLine.class))).thenReturn(line);

        PurchaseLine result = purchaseLineService.addPurchaseLine(line);
        assertNotNull(result);
        assertEquals(3, result.getId());
    }

    @Test
    void testUpdatePurchaseLine() {
        PurchaseLine line = new PurchaseLine(1, 101, 202, 29.99);
        when(purchaseLineRepository.findById(1)).thenReturn(Optional.of(line));
        when(purchaseLineRepository.save(any(PurchaseLine.class))).thenReturn(line);

        PurchaseLine updatedLine = new PurchaseLine(1, 101, 202, 35.99);
        PurchaseLine result = purchaseLineService.updatePurchaseLine(1, updatedLine);

        assertNotNull(result);
        assertEquals(35.99, result.getPrix());
    }

    @Test
    void testUpdatePurchaseLineNotFound() {
        PurchaseLine updatedLine = new PurchaseLine(1, 101, 202, 35.99);
        when(purchaseLineRepository.findById(1)).thenReturn(Optional.empty());

        PurchaseLine result = purchaseLineService.updatePurchaseLine(1, updatedLine);
        assertNull(result);
    }

    @Test
    void testDeletePurchaseLine() {
        doNothing().when(purchaseLineRepository).deleteById(1);
        purchaseLineService.deletePurchaseLine(1);
        verify(purchaseLineRepository, times(1)).deleteById(1);
    }
}
