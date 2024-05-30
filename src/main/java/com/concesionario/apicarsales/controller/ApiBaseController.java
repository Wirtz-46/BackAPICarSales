package com.concesionario.apicarsales.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@ControllerAdvice
@OpenAPIDefinition(
    info = @Info(
        title = "Api CarSales",
        version = "1.0",
        description = "Api para el control de ventas de autos"
    ),
    tags = {
        @Tag(
            name = "Base Controller",
            description = "Controlador base para la api, este controlador se extendera a todos los endpoints"
        )
    }

)
public class ApiBaseController {
    
}
