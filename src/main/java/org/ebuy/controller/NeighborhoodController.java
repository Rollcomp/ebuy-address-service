package org.ebuy.controller;

import org.ebuy.model.Neighborhood;
import org.ebuy.model.mapper.NeighborhoodMapper;
import org.ebuy.model.request.NeighborhoodRequest;
import org.ebuy.model.response.NeighborhoodDto;
import org.ebuy.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Burak Köken on 9.5.2020.
 */
@RestController
@RequestMapping("/location/district/neighborhood")
public class NeighborhoodController {

    private NeighborhoodService neighborhoodService;
    private NeighborhoodMapper neighborhoodMapper;

    @Autowired
    public NeighborhoodController(NeighborhoodService neighborhoodService,
                                  NeighborhoodMapper neighborhoodMapper) {
        this.neighborhoodService = neighborhoodService;
        this.neighborhoodMapper = neighborhoodMapper;
    }

    @GetMapping("/{neighborhoodId}")
    public ResponseEntity<NeighborhoodDto> getNeighborhood(@PathVariable Long neighborhoodId) {
        Neighborhood neighborhood = neighborhoodService.findNeighborhoodById(neighborhoodId);
        return ResponseEntity.ok(neighborhoodMapper.toNeighborhoodDto(neighborhood));
    }

    @PostMapping
    public ResponseEntity<NeighborhoodDto> createNeighborhood(@RequestBody NeighborhoodRequest request) {
        Neighborhood savedNeighborhood = neighborhoodService.createNeighborhood(neighborhoodMapper.toNeighborhood(request));
        return ResponseEntity.ok(neighborhoodMapper.toNeighborhoodDto(savedNeighborhood));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<NeighborhoodDto>> createBulkNeighborhood(@RequestBody List<NeighborhoodRequest> request) {
        List<Neighborhood> neighborhoods = neighborhoodService.createBulkNeighborhoods(neighborhoodMapper.toNeighborhoodList(request));
        return ResponseEntity.ok(neighborhoodMapper.toNeighborhoodDtoList(neighborhoods));
    }

    @PutMapping("/{neighborhoodId}")
    public ResponseEntity<NeighborhoodDto> updateNeighborhoodId(@PathVariable Long neighborhoodId, @RequestBody NeighborhoodRequest request) {
        Neighborhood updatedNeighborhood = neighborhoodMapper.toNeighborhood(request);
        Neighborhood result = neighborhoodService.updateNeighborhood(neighborhoodId, updatedNeighborhood);
        return ResponseEntity.ok(neighborhoodMapper.toNeighborhoodDto(result));
    }

    @DeleteMapping("/{neighborhoodId}")
    public ResponseEntity<?> deleteNeighborhoodId(@PathVariable Long neighborhoodId) {
        neighborhoodService.deleteNeighborhood(neighborhoodId);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

}
