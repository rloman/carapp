package com.ilionx.carapp.api;

import com.ilionx.carapp.model.Car;
import com.ilionx.carapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CarController.url)
public class CarController {

    public static final String url = "/api/cars";

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<Car> create(@RequestBody Car car) {
        return ResponseEntity.ok(this.carService.save(car));
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAllCars() {
        return ResponseEntity.ok(this.carService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Car> findCarById(@PathVariable  long id) {

        Optional<Car> optionalCar = this.carService.findById(id);
        if (optionalCar.isPresent()) {
            return ResponseEntity.ok(optionalCar.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Car>  updateCarById(@PathVariable  long id, @RequestBody Car source) {

        Optional<Car> optionalCar = this.carService.update(source, id);
        if (optionalCar.isPresent()) {
            return ResponseEntity.ok(optionalCar.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@PathVariable  long id) {
        this.carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("mileage/{mileage}")
    public List<Car> findByMileage(@PathVariable int mileage) {
        return carService.findByMileage(mileage);
    }

    @GetMapping("invalids")
    public ResponseEntity<List<Car>> findInvalids() {
        return ResponseEntity.ok(this.carService.findInvalids());
    }
}
