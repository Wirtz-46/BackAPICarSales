# Car Sales Management API

## Descripción

Este proyecto consiste en una API para la gestión de ventas de carros, que incluye las entidades de Clientes, Carros y Ventas. Proporciona endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre estas entidades y está conectado a una base de datos MySQL. La comunicación se realiza a través del protocolo HTTP.

## Estructura del Proyecto

El proyecto contiene los siguientes controladores principales:

- `SaleController`: Maneja las operaciones relacionadas con las ventas.
- `ClientController`: Maneja las operaciones relacionadas con los clientes.
- `CarController`: Maneja las operaciones relacionadas con los carros.

## Requisitos

- Java 11 o superior
- Spring Boot
- MySQL

## Configuración

1. Clona el repositorio:
    ```bash
    git clone https://github.com/tu-usuario/car-sales-management.git
    cd car-sales-management
    ```

2. Configura la base de datos en el archivo `application.properties`:
    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/name
    spring.datasource.username=user
    spring.datasource.password=password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
    ```


3. Ejecuta la aplicación:
    ```bash
    ./mvnw spring-boot:run
    ```

## Endpoints

### Sales

- **Crear una venta**
    ```http
    POST /sales
    ```
    **Parámetros:**
    - `clientId`: ID del cliente (Long)
    - `carIds`: Lista de IDs de carros (List<Long>)
    - `total`: Total de la venta (BigDecimal)

- **Obtener una venta por ID**
    ```http
    GET /sales/{id}
    ```

- **Obtener todas las ventas**
    ```http
    GET /sales
    ```

- **Eliminar una venta por ID**
    ```http
    DELETE /sales/{id}
    ```

- **Actualizar una venta**
    ```http
    PUT /sales/{id}
    ```
    **Cuerpo:** Objeto `Sale`

### Clients

- **Obtener todos los clientes**
    ```http
    GET /clients
    ```

- **Obtener un cliente por ID**
    ```http
    GET /clients/{id}
    ```

- **Crear un cliente**
    ```http
    POST /clients
    ```
    **Cuerpo:** Objeto `Client`

- **Actualizar un cliente por ID**
    ```http
    PUT /clients/{id}
    ```
    **Cuerpo:** Objeto `Client`

- **Eliminar un cliente por ID**
    ```http
    DELETE /clients/{id}
    ```

### Cars

- **Obtener todos los carros o buscar por número de serie**
    ```http
    GET /cars
    ```
    **Parámetros Opcionales:**
    - `serialNumber`: Número de serie del carro (String)

- **Obtener un carro por ID**
    ```http
    GET /cars/{id}
    ```

- **Crear un carro**
    ```http
    POST /cars
    ```
    **Cuerpo:** Objeto `Car`

- **Actualizar un carro por ID**
    ```http
    PUT /cars/{id}
    ```
    **Cuerpo:** Objeto `Car`

- **Eliminar un carro por ID**
    ```http
    DELETE /cars/{id}
    ```


