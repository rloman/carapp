package com.ilionx.carapp.service;

import com.ilionx.carapp.model.Car;
import com.ilionx.carapp.persistence.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> findByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    public Car save(Car car) {
        return this.carRepository.save(car);
    }

    public Optional<Car> findByLicensePlate(String licensePlate) {
        return carRepository.findByLicensePlate(licensePlate);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Optional<Car> findById(Long aLong) {
        return carRepository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        carRepository.deleteById(aLong);
    }
}
