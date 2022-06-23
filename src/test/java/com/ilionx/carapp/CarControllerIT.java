package com.ilionx.carapp;

import com.ilionx.carapp.api.CarController;
import com.ilionx.carapp.model.Car;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationtest")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarControllerIT {

    private static long currentId = -1;

    @Autowired
    private TestRestTemplate restTemplate; // sort of Postman



    @Test
    @Order(1)
    public void testCreateUsingPost() {
        Car car = new Car();
        car.setLicensePlate("GZ120H");
        car.setBrand("Kia");
        car.setMileage(97001);

        ResponseEntity<Car> response  = this.restTemplate.postForEntity(CarController.url, car, Car.class);
        assertEquals(200, response.getStatusCodeValue());

        Car returnedCar = response.getBody();
        assertNotEquals(0, returnedCar.getId());
        assertNotNull(returnedCar);
        assertEquals("Kia", returnedCar.getBrand());
        currentId = returnedCar.getId();
    }

    @Test
    @Order(2)
    public void testFetchit() {
        ResponseEntity<Car> response  = this.restTemplate.getForEntity(CarController.url+"/"+currentId, Car.class);
        assertNotNull(response.getBody());
        Car responsedCar = response.getBody();
        assertEquals(97001, responsedCar.getMileage());
    }
}
