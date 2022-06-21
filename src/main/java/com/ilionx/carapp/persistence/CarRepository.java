package com.ilionx.carapp.persistence;

import com.ilionx.carapp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByBrand(String brand);
    Optional<Car> findByLicensePlate(String licensePlate);

    @Query(value = "select id, brand, license_plate, mileage from car where mileage=?1", nativeQuery = true)
    List<Car> findByMileage(int mileage);
}
