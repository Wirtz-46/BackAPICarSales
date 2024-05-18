package com.concesionario.apicarsales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.concesionario.apicarsales.model.Car;
import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findBySerialNumber(String serialNumber);
}
