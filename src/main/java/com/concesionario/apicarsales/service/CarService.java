package com.concesionario.apicarsales.service;

import java.util.List;
import com.concesionario.apicarsales.model.Car;

public interface CarService {
    
    List<Car> getAll();
    Car getById(Long clientId);
    List<Car> getByserialNumber(String serialNumber);
    Car addCar(Car car);
    Car updateCar(Long clientId, Car newCar);
    void deleteCar(Long clientId);

}
