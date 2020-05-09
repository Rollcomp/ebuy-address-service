package org.ebuy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Burak Köken on 9.5.2020.
 */
@RestController
@RequestMapping("/location/city")
public class CityController {

    @GetMapping
    public ResponseEntity<?> getAllCities() {
        return null;
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<?> getCity(@PathVariable Long cityId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createCity() {
        return null;
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<?> updateCity(@PathVariable Long cityId) {
        return null;
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<?> deleteCity(@PathVariable Long cityId) {
        return null;
    }

    @GetMapping("/{cityId}/district")
    public ResponseEntity<?> getDistricts(@PathVariable Long cityId) {
        return null;
    }

    @GetMapping("/{cityId}/district/{districtId}/neighborhood")
    public ResponseEntity<?> getNeighborhoodOfCityDistrict(@PathVariable Long cityId, @PathVariable Long districtId) {
        return null;
    }

}
