package org.ebuy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Burak Köken on 9.5.2020.
 */
@RestController
@RequestMapping("/location/district/neighborhood")
public class NeighborhoodController {

    @GetMapping("/{neighborhoodId}")
    public ResponseEntity<?> getNeighborhood(@PathVariable Long neighborhoodId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createNeighborhoodId() {
        return null;
    }

    @PutMapping("/{neighborhoodId}")
    public ResponseEntity<?> updateNeighborhoodId(@PathVariable Long neighborhoodId) {
        return null;
    }

    @DeleteMapping("/{neighborhoodId}")
    public ResponseEntity<?> deleteNeighborhoodId(@PathVariable Long neighborhoodId) {
        return null;
    }

}
