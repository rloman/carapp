package com.ilionx.carapp.service;

import com.ilionx.carapp.model.Car;
import com.ilionx.carapp.persistence.CarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Demo of Mockito
public class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    public CarServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {

        // Given
        {
            Car mockedCar = new Car();
            mockedCar.setId(3);
            mockedCar.setBrand("Mitsubishi");
            Optional<Car> mockedOptionalCar = Optional.of(mockedCar);
            Mockito.when(this.carRepository.findById(3L)).thenReturn(mockedOptionalCar);
        }

        // When (this is the test invocation)
        Optional<Car> optionalCar = this.carService.findById(3L);

        // Then
        assertEquals(3L, optionalCar.get().getId());
        assertEquals("Mitsubishi", optionalCar.get().getBrand());
    }
}