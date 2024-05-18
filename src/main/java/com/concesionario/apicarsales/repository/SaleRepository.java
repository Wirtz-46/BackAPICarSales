package com.concesionario.apicarsales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.concesionario.apicarsales.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    
}
