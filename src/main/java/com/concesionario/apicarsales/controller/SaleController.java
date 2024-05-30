package com.concesionario.apicarsales.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.concesionario.apicarsales.model.Sale;
import com.concesionario.apicarsales.service.SaleService;

@RestController
@RequestMapping("/apicarsales/sales")
public class SaleController extends ApiBaseController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public Sale createSale(@RequestParam("clientId") Long clientId,
            @RequestParam("carIds") List<Long> carIds,
            @RequestParam("total") BigDecimal total) {
        return saleService.createSale(clientId, carIds, total);
    }

    @GetMapping("/{id}")
    public Sale findSaleById(@PathVariable("id") Long id) {
        return saleService.findSaleById(id);
    }

    @GetMapping
    public List<Sale> findAllSales() {
        return saleService.findAllSales();
    }

    @DeleteMapping("/{id}")
    public void deleteSaleById(@PathVariable("id") Long id) {
        saleService.deleteSaleById(id);
    }

    @PutMapping("/{id}")
    public Sale updateSale(@PathVariable("id") Long id,
            @RequestBody Sale sale) {
        return saleService.updateSale(id, sale);
    }
}
