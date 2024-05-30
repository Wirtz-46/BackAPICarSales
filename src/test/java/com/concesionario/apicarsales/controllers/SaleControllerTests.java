package com.concesionario.apicarsales.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.concesionario.apicarsales.controller.SaleController;
import com.concesionario.apicarsales.model.Sale;
import com.concesionario.apicarsales.service.SaleService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SaleController.class)
public class SaleControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private SaleService saleService;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateSale() throws Exception {
        Sale sale = new Sale();
        sale.setId(1L);

        when(saleService.createSale(any(Long.class), anyList(), any(BigDecimal.class))).thenReturn(sale);

        mockMvc.perform(MockMvcRequestBuilders.post("/apicarsales/sales")
                .contentType(MediaType.APPLICATION_JSON)
                .param("clientId", "1")
                .param("carIds", "1,2,3")
                .param("total", "10000.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(saleService, times(1)).createSale(any(Long.class), anyList(), any(BigDecimal.class));
    }

    @Test
    public void testFindSaleById() throws Exception {
        Sale sale = new Sale();
        sale.setId(1L);

        when(saleService.findSaleById(1L)).thenReturn(sale);

        mockMvc.perform(MockMvcRequestBuilders.get("/apicarsales/sales/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(saleService, times(1)).findSaleById(1L);
    }

    @Test
    public void testFindAllSales() throws Exception {
        List<Sale> sales = new ArrayList<>();
        Sale sale1 = new Sale();
        sale1.setId(1L);
        Sale sale2 = new Sale();
        sale2.setId(2L);
        sales.add(sale1);
        sales.add(sale2);

        when(saleService.findAllSales()).thenReturn(sales);

        mockMvc.perform(MockMvcRequestBuilders.get("/apicarsales/sales"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(saleService, times(1)).findAllSales();
    }

    @Test
    public void testDeleteSaleById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/apicarsales/sales/1"))
                .andExpect(status().isOk());

        verify(saleService, times(1)).deleteSaleById(1L);
    }

    @Test
    public void testUpdateSale() throws Exception {
        Sale sale = new Sale();
        sale.setId(1L);

        when(saleService.updateSale(any(Long.class), any(Sale.class))).thenReturn(sale);

        mockMvc.perform(MockMvcRequestBuilders.put("/apicarsales/sales/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"total\": 15000.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(saleService, times(1)).updateSale(any(Long.class), any(Sale.class));
    }
}
