package com.ilionx.carapp.service;

import com.ilionx.carapp.model.Car;
import com.ilionx.carapp.persistence.CarRepository;
import com.ilionx.carapp.validator.KentekenValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    @Autowired
    private KentekenValidator kentekenValidator;

    @Autowired
    private CarRepository carRepository;

    public List<Car> findByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    @Transactional
    public Car save(Car car) {
        Car savedCar = this.carRepository.save(car);
        // if car.brand == 'stophier' then it demo's a save and a Transaction rollback

        return savedCar;
    }

    @Transactional
    public Optional<Car> update(Car source, long id) {
        Optional<Car> optionalCar = this.carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car target = optionalCar.get();
            target.setBrand(source.getBrand());
            target.setLicensePlate(source.getLicensePlate());
            target.setMileage(source.getMileage());

            return Optional.of(this.save(target));
        } else {
            return Optional.empty();
        }
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

    @Transactional
    public void deleteById(Long aLong) {
        carRepository.deleteById(aLong);
    }

    public List<Car> findByMileage(int mileage) {
        return carRepository.findByMileage(mileage);
    }

    // No transaction will (ever) be started here since it is a call to a method in the SAME CLASS!!!
    public void foo() {
        this.save(new Car());
    }

    public List<Car> findInvalids() {
        System.err.println(KentekenValidator.REGEXP_KENTEKEN_FORMAT);

        return this.carRepository.findInvalids(KentekenValidator.REGEXP_KENTEKEN_FORMAT);
    }
}
