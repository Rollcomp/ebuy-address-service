package org.ebuy.service;

import org.ebuy.exception.CityNotFoundException;
import org.ebuy.model.City;
import org.ebuy.model.District;
import org.ebuy.model.Neighborhood;
import org.ebuy.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    public City findCityById(Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        return cityOptional.orElseThrow(() -> new CityNotFoundException(id));
    }

    public City createCity(City city) {
        return null;
    }

    public List<City> createBulkCity(List<City> cities) {
        return null;
    }

    public City updateCity(Long cityId, City city) {
        return null;
    }

    public void deleteCity(Long cityId) {
        throw new RuntimeException();
    }

    public List<District> findDistricts(Long cityId) {
        return null;
    }

    public List<Neighborhood> findNeighborhoods(Long cityId, Long districtId) {
        return null;
    }

}
