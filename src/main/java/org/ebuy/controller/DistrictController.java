package org.ebuy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Burak Köken on 9.5.2020.
 */
@RestController
@RequestMapping("/location/district/")
public class DistrictController {

    @GetMapping("/{districtId}")
    public ResponseEntity<?> getDistrict(@PathVariable Long districtId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createDistrict() {
        return null;
    }

    @PutMapping("/{districtId}")
    public ResponseEntity<?> updateDistrict(@PathVariable Long districtId) {
        return null;
    }

    @DeleteMapping("/{districtId}")
    public ResponseEntity<?> deleteDistrict(@PathVariable Long districtId) {
        return null;
    }

}
