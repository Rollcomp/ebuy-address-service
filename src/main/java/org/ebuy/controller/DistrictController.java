package org.ebuy.controller;

import org.ebuy.model.District;
import org.ebuy.model.mapper.DistrictMapper;
import org.ebuy.model.request.DistrictRequest;
import org.ebuy.model.response.DistrictDto;
import org.ebuy.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Burak Köken on 9.5.2020.
 */
@RestController
@RequestMapping("/location/district/")
public class DistrictController {

    private DistrictService districtService;
    private DistrictMapper districtMapper;

    @Autowired
    public DistrictController(DistrictService districtService,
                              DistrictMapper districtMapper) {
        this.districtService = districtService;
        this.districtMapper = districtMapper;
    }

    @GetMapping("/{districtId}")
    public ResponseEntity<DistrictDto> getDistrict(@PathVariable Long districtId) {
        District district = districtService.findDistrictById(districtId);
        return ResponseEntity.ok(districtMapper.toDistrictDto(district));
    }

    @PostMapping
    public ResponseEntity<DistrictDto> createDistrict(@RequestBody DistrictRequest request) {
        District savedDistrict = districtService.createDistrict(districtMapper.toDistrict(request));
        return ResponseEntity.ok(districtMapper.toDistrictDto(savedDistrict));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<DistrictDto>> createBulkDistrict(@RequestBody List<DistrictRequest> request) {
        List<District> districts = districtService.createBulkDistrict(districtMapper.toDistrictList(request));
        return ResponseEntity.ok(districtMapper.toDistrictDtoList(districts));
    }

    @PutMapping("/{districtId}")
    public ResponseEntity<DistrictDto> updateDistrict(@PathVariable Long districtId, @RequestBody DistrictRequest request) {
        District updatedDistrict = districtMapper.toDistrict(request);
        District result = districtService.updateDistrict(districtId, updatedDistrict);
        return ResponseEntity.ok(districtMapper.toDistrictDto(result));
    }

    @DeleteMapping("/{districtId}")
    public ResponseEntity<?> deleteDistrict(@PathVariable Long districtId) {
        districtService.deleteDistrict(districtId);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

}
