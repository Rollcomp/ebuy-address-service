package org.ebuy.controller;

import org.ebuy.model.City;
import org.ebuy.model.District;
import org.ebuy.model.Neighborhood;
import org.ebuy.model.mapper.CityMapper;
import org.ebuy.model.mapper.DistrictMapper;
import org.ebuy.model.mapper.NeighborhoodMapper;
import org.ebuy.model.request.CityRequest;
import org.ebuy.model.response.CityDto;
import org.ebuy.model.response.DistrictDto;
import org.ebuy.model.response.NeighborhoodDto;
import org.ebuy.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Burak Köken on 9.5.2020.
 */
@RestController
@RequestMapping("/location/city")
public class CityController {

    private CityService cityService;
    private CityMapper cityMapper;
    private DistrictMapper districtMapper;
    private NeighborhoodMapper neighborhoodMapper;

    @Autowired
    public CityController(CityService cityService,
                          CityMapper cityMapper,
                          DistrictMapper districtMapper,
                          NeighborhoodMapper neighborhoodMapper) {
        this.cityService = cityService;
        this.cityMapper = cityMapper;
        this.districtMapper = districtMapper;
        this.neighborhoodMapper = neighborhoodMapper;
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> getAllCities() {
        List<City> cities = cityService.findAllCities();
        return ResponseEntity.ok(cityMapper.toCityDtoList(cities));
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<CityDto> getCity(@PathVariable Long cityId) {
        City city = cityService.findCityById(cityId);
        return ResponseEntity.ok(cityMapper.toCityDto(city));
    }

    @PostMapping
    public ResponseEntity<CityDto> createCity(@RequestBody CityRequest request) {
        City savedCity = cityService.createCity(cityMapper.toCity(request));
        return ResponseEntity.ok(cityMapper.toCityDto(savedCity));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<CityDto>> createBulkCity(@RequestBody List<CityRequest> request) {
        List<City> cities = cityService.createBulkCity(cityMapper.toCityList(request));
        return ResponseEntity.ok(cityMapper.toCityDtoList(cities));
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<CityDto> updateCity(@PathVariable Long cityId, @RequestBody CityRequest request) {
        City updatedCity = cityMapper.toCity(request);
        City result = cityService.updateCity(cityId, updatedCity);
        return ResponseEntity.ok(cityMapper.toCityDto(result));
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<?> deleteCity(@PathVariable Long cityId) {
        cityService.deleteCity(cityId);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @GetMapping("/{cityId}/district")
    public ResponseEntity<List<DistrictDto>> getDistricts(@PathVariable Long cityId) {
        List<District> districts = cityService.findDistrictsByCityId(cityId);
        return ResponseEntity.ok(districtMapper.toDistrictDtoList(districts));
    }

    @GetMapping("/{cityId}/district/{districtId}/neighborhood")
    public ResponseEntity<List<NeighborhoodDto>> getNeighborhoodOfCityDistrict(@PathVariable Long cityId, @PathVariable Long districtId) {
        List<Neighborhood> neighborhoods = cityService.findNeighborhoodByCityAndDistrictId(cityId, districtId);
        return ResponseEntity.ok(neighborhoodMapper.toNeighborhoodDtoList(neighborhoods));
    }

}
