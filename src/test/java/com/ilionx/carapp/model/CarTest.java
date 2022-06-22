package com.ilionx.carapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {

    // class under test
    private Car car = new Car();

    @Test
    public void testSetGetBrand() {
        car.setBrand("Ferrari");
        assertEquals("Ferrari", car.getBrand());
    }

    @Test
    public void testSetGetMileage() {
        car.setMileage(110000);
        assertEquals(110000, car.getMileage());
    }
}
