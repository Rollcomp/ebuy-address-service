package org.ebuy.controller;

import org.ebuy.model.request.NeighborhoodRequest;
import org.ebuy.model.response.NeighborhoodDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Burak Köken on 9.5.2020.
 */
@RestController
@RequestMapping("/location/district/neighborhood")
public class NeighborhoodController {

    @GetMapping("/{neighborhoodId}")
    public ResponseEntity<NeighborhoodDto> getNeighborhood(@PathVariable Long neighborhoodId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<NeighborhoodDto> createNeighborhoodId(@RequestBody NeighborhoodRequest request) {
        return null;
    }

    @PutMapping("/{neighborhoodId}")
    public ResponseEntity<NeighborhoodDto> updateNeighborhoodId(@PathVariable Long neighborhoodId, @RequestBody NeighborhoodRequest request) {
        return null;
    }

    @DeleteMapping("/{neighborhoodId}")
    public ResponseEntity<?> deleteNeighborhoodId(@PathVariable Long neighborhoodId) {
        return null;
    }

}
