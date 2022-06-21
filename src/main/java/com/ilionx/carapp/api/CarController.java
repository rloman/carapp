package com.ilionx.carapp.api;

import com.ilionx.carapp.model.Car;
import com.ilionx.carapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> findAllCars() {
        return this.carService.findAll();
    }

    @PostMapping
    public Car create(@RequestBody Car car) {
        return this.carService.save(car);
    }

    @GetMapping("{id}")
    public ResponseEntity<Car> findCarById(@PathVariable  long id) {

        Optional<Car> optionalCar = this.carService.findById(id);
        if (optionalCar.isPresent()) {
            return new ResponseEntity<>(optionalCar.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Car>  updateCarById(@PathVariable  long id, @RequestBody Car origin) {

        Optional<Car> optionalCar = this.carService.findById(id);
        if (optionalCar.isPresent()) {
            Car target = optionalCar.get();
            target.setBrand(origin.getBrand());
            target.setLicensePlate(origin.getLicensePlate());
            target.setMileage(origin.getMileage());

            return new ResponseEntity<>(this.carService.save(target), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
