package com.ilionx.carapp.model;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarTest {

    // class under test
    private Car car;

    @BeforeEach
    public void init() {
        this.car = new Car();
    }

    @Test
    public void testSetGetBrand() {
        car.setBrand("Ferrari");
        Assertions.assertEquals("Ferrari", car.getBrand());
    }

    @Test
    public void testSetGetMileage() {
        car.setMileage(110000);
        Assertions.assertEquals(110000, car.getMileage());
    }
}
