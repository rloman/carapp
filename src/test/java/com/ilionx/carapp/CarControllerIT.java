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
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationtest")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarControllerIT {

    private static long currentId = -1;

    @Autowired
    private TestRestTemplate restTemplate; // sort of Postman

    @LocalServerPort
    private int localServerPort;

    @Test
    @Order(1)
    public void testCreateUsingPost() {
        {
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
            assertEquals("GZ120H", returnedCar.getLicensePlate());
            currentId = returnedCar.getId();
        }
        {
            Car car = new Car();
            car.setLicensePlate("R-095-FK");
            car.setBrand("Kia");
            car.setMileage(5306);

            ResponseEntity<Car> response  = this.restTemplate.postForEntity(CarController.url, car, Car.class);
            assertEquals(200, response.getStatusCodeValue());

            Car returnedCar = response.getBody();
            assertNotEquals(0, returnedCar.getId());
            assertNotNull(returnedCar);
            assertEquals("Kia", returnedCar.getBrand());
            assertEquals("R-095-FK", returnedCar.getLicensePlate());
            currentId = returnedCar.getId();
        }

    }

    @Test
    @Order(2)
    public void testList() {
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        // Act
        ResponseEntity<List<Car>> response = restTemplate.exchange(CarController.url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Car>>() {
                });
        List<Car> responseBodyAsList = response.getBody();

        // Assert
        assertEquals(2, responseBodyAsList.size());

        int counter = 0;
        for(Car car: responseBodyAsList ) {
            if(car.getLicensePlate().equals("R-095-FK") && car.getMileage()==5306) {
                counter++;
            }
            if("GZ120H".equals(car.getLicensePlate()) && car.getMileage()==97001) {
                counter++;
            }
        }
        assertEquals(2, counter);
    }

    @Test
    @Order(3)
    public void testFetchit() {
        ResponseEntity<Car> response  = this.restTemplate.getForEntity(CarController.url+"/"+currentId, Car.class);
        assertNotNull(response.getBody());
        Car responsedCar = response.getBody();
        assertEquals(5306, responsedCar.getMileage());
    }
}
