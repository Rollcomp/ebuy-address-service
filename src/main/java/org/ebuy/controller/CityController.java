package org.ebuy.controller;

import org.ebuy.model.request.CityRequest;
import org.ebuy.model.response.CityDto;
import org.ebuy.model.response.DistrictDto;
import org.ebuy.model.response.NeighborhoodDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Burak Köken on 9.5.2020.
 */
@RestController
@RequestMapping("/location/city")
public class CityController {

    @GetMapping
    public ResponseEntity<List<CityDto>> getAllCities() {
        return null;
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<CityDto> getCity(@PathVariable Long cityId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<CityDto> createCity(@RequestBody CityRequest request) {
        return null;
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<CityDto> updateCity(@PathVariable Long cityId, @RequestBody CityRequest request) {
        return null;
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<?> deleteCity(@PathVariable Long cityId) {
        return null;
    }

    @GetMapping("/{cityId}/district")
    public ResponseEntity<List<DistrictDto>> getDistricts(@PathVariable Long cityId) {
        return null;
    }

    @GetMapping("/{cityId}/district/{districtId}/neighborhood")
    public ResponseEntity<List<NeighborhoodDto>> getNeighborhoodOfCityDistrict(@PathVariable Long cityId, @PathVariable Long districtId) {
        return null;
    }

}
