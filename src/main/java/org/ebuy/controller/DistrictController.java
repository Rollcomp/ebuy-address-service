package org.ebuy.controller;

import org.ebuy.model.request.CityRequest;
import org.ebuy.model.request.DistrictRequest;
import org.ebuy.model.response.CityDto;
import org.ebuy.model.response.DistrictDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Burak Köken on 9.5.2020.
 */
@RestController
@RequestMapping("/location/district/")
public class DistrictController {

    @GetMapping("/{districtId}")
    public ResponseEntity<DistrictDto> getDistrict(@PathVariable Long districtId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<DistrictDto> createDistrict(@RequestBody DistrictRequest request) {
        return null;
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<DistrictDto>> createBulkDistrict(@RequestBody List<DistrictDto> request) {
        return null;
    }

    @PutMapping("/{districtId}")
    public ResponseEntity<DistrictDto> updateDistrict(@PathVariable Long districtId, @RequestBody DistrictRequest request) {
        return null;
    }

    @DeleteMapping("/{districtId}")
    public ResponseEntity<?> deleteDistrict(@PathVariable Long districtId) {
        return null;
    }

}
