package com.concesionario.apicarsales.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.concesionario.apicarsales.model.Car;
import com.concesionario.apicarsales.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService{
    //serialNumber
     @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Car getById(Long id) {
        return carRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("The car with ID " + id + " doesn't exist."));
    }

    @Override
    public List<Car> getByserialNumber(String serialNumber){
        return carRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public Car addCar(Car car){
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, Car newCar){
        if (carRepository.existsById(null)) {
            newCar.setId(id);
            return carRepository.save(newCar);
        }else{
            throw new IllegalArgumentException("The car with ID " + id + " doesn't exist.");
        }
    }

    @Override
    public void deleteCar(Long id){
        carRepository.deleteById(id);
    }


}
