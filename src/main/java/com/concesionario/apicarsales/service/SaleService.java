package com.concesionario.apicarsales.service;

import java.math.BigDecimal;
import java.util.List;

import com.concesionario.apicarsales.model.Sale;

public interface SaleService {
    
    Sale createSale(Long clientId, List<Long> carIds, BigDecimal total);

    Sale findSaleById(Long id);

    List<Sale> findAllSales();

    void deleteSaleById(Long id);

    Sale updateSale(Long id, Sale sale);
}
