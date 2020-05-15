package org.ebuy.service;

import org.ebuy.exception.CityNotFoundException;
import org.ebuy.exception.DistrictNotFoundException;
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

    private DistrictService districtService;

    private NeighborhoodService neighborhoodService;

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
        return cityRepository.save(city);
    }

    public List<City> createBulkCity(List<City> cities) {
        return cityRepository.saveAll(cities);
    }

    public City updateCity(Long cityId, City updatedCity) {
        Optional<City> cityOptional = cityRepository.findById(cityId);
        City city = cityOptional.orElseThrow(() -> new CityNotFoundException(cityId));
        city.setName(updatedCity.getName());
        city.setCode(updatedCity.getCode());
        city.setCountryCode(updatedCity.getCountryCode());
        return cityRepository.save(city);
    }

    public void deleteCity(Long cityId) {
        Optional<City> cityOptional = cityRepository.findById(cityId);
        City city = cityOptional.orElseThrow(() -> new CityNotFoundException(cityId));
        cityRepository.delete(city);
    }

    public List<District> findDistrictsByCityId(Long cityId) {
        Optional<City> cityOptional = cityRepository.findById(cityId);
        cityOptional.orElseThrow(() -> new CityNotFoundException(cityId));
        return districtService.findDistricts(cityId);
    }

    public List<Neighborhood> findNeighborhoodByCityAndDistrictId(Long cityId, Long districtId) {
        Optional<City> cityOptional = cityRepository.findById(cityId);
        City city = cityOptional.orElseThrow(() -> new CityNotFoundException(cityId));
        Optional<District> districtOptional = city.getDistricts().stream().filter(d -> d.getId() == districtId).findFirst();
        District district = districtOptional.orElseThrow(() -> new DistrictNotFoundException(districtId));
        return neighborhoodService.findNeighborhoods(district.getId());
    }

}
