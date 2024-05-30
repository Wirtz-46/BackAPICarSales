package com.concesionario.apicarsales.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

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

import com.concesionario.apicarsales.controller.CarController;
import com.concesionario.apicarsales.model.Car;
import com.concesionario.apicarsales.service.CarService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(CarController.class)
public class CarControllerTests {
    
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CarService carService;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllCars() throws Exception {
        when(carService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/apicarsales/cars"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(carService, times(1)).getAll();
    }

    @Test
    public void testGetCarById() throws Exception {
        Car car = new Car();
        car.setId(1L);
        when(carService.getById(1L)).thenReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.get("/apicarsales/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(carService, times(1)).getById(1L);
    }

    @Test
    public void testCreateCar() throws Exception {
        Car car = new Car();
        car.setId(1L);
        when(carService.addCar(any(Car.class))).thenReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.post("/apicarsales/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": null, \"brand\": \"Toyota\", \"model\": \"Corolla\", \"serialNumber\": \"123ABC\", \"price\": 20000.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(carService, times(1)).addCar(any(Car.class));
    }

    @Test
    public void testUpdateCar() throws Exception {
        Car car = new Car();
        car.setId(1L);
        when(carService.updateCar(any(Long.class), any(Car.class))).thenReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.put("/apicarsales/cars/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": null, \"brand\": \"Toyota\", \"model\": \"Corolla\", \"serialNumber\": \"123ABC\", \"price\": 20000.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(carService, times(1)).updateCar(any(Long.class), any(Car.class));
    }

    @Test
    public void testDeleteCar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/apicarsales/cars/1"))
                .andExpect(status().isNoContent());

        verify(carService, times(1)).deleteCar(1L);
    }
}

