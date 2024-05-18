package com.concesionario.apicarsales.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.concesionario.apicarsales.model.Car;
import com.concesionario.apicarsales.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
    
    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> buscarLibros(@RequestParam(required = false) String serialNumber){
        if (serialNumber != null && !serialNumber.isEmpty()) {
            return carService.getByserialNumber(serialNumber);
        }else{
            return carService.getAll();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> obtenerProductoPorId(@PathVariable Long id){
        Car car = carService.getById(id);
        if (car != null) {
            return ResponseEntity.ok(car);
        }else{
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        Car newCar = carService.addCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car newCar){
        Car updatedCar = carService.updateCar(id, newCar);
        if (updatedCar != null) {
            return ResponseEntity.ok(updatedCar);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

}
