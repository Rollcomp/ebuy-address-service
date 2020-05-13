package org.ebuy.service;

import org.ebuy.exception.CityNotFoundException;
import org.ebuy.helper.HelperMethods;
import org.ebuy.model.City;
import org.ebuy.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CityServiceIntegrationTest {

    @Mock
    private CityRepository cityRepository;

    @Spy
    @InjectMocks
    private CityService cityService;

    @Test
    public void givenCityIdNotInDb_whenFindCityByIdIsCalled_thenShouldThrowCityNotFoundException() throws Exception {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(CityNotFoundException.class, () -> {
            cityService.findCityById(anyLong());
        });
    }

    @Test
    public void whenFindAllCitiesIsCalled_thenShouldReturnCitiesCorrectly() {
        List<City> cities = new ArrayList<>();
        cities.add(HelperMethods.buildCity("İstanbul", "34"));
        cities.add(HelperMethods.buildCity("Ankara", "06"));
        when(cityRepository.findAll()).thenReturn(cities);
        assertThat(cityService.findAllCities()).hasSize(2);
    }

    @Test
    public void givenCityValues_whenCreateCityIsCalled_thenShouldSaveCity() {
        City result = HelperMethods.buildCity(1L, "İstanbul", "34");
        when(cityRepository.save(any())).thenReturn(Optional.of(result));
        City city = HelperMethods.buildCity("İstanbul", "34");
        assertThat(cityService.createCity(city).getId()).isNotNull();
    }

    @Test
    public void givenSeveralCityValues_whenCreateBulkCityIsCalled_thenCityShouldBeSaved() {
        List<City> resultList = new ArrayList<>();
        resultList.add(HelperMethods.buildCity(1L, "İstanbul", "34"));
        resultList.add(HelperMethods.buildCity(2L,"Ankara", "06"));
        when(cityRepository.saveAll(any())).thenReturn(resultList);

        List<City> cities = new ArrayList<>();
        cities.add(HelperMethods.buildCity("İstanbul", "34"));
        cities.add(HelperMethods.buildCity("Ankara", "06"));

        List<City> result = cityService.createBulkCity(cities);
        assertThat(result).hasSize(2);
        result.forEach(c -> assertThat(c.getId()).isNotNull());
    }

    @Test
    public void givenUpdatedCityValuesForAnyCityExistingInDB_whenUpdateCityIsCalled_thenCityShouldBeUpdated() {
        City city1 = HelperMethods.buildCity(1L, "İstanbul", "34");
        when(cityRepository.findById(any())).thenReturn(Optional.of(city1));
        City city2 = HelperMethods.buildCity(1L, "Kocaeli", "41");
        when(cityRepository.save(any())).thenReturn(Optional.of(city2));
        City result = cityService.updateCity(1L, city2);
        assertThat(result.getName()).isEqualTo("Kocaeli");
        assertThat(result.getCode()).isEqualTo("41");
    }

    @Test
    public void givenUpdatedCityValuesForAnyCityNonExistingInDB_whenUpdateCityIsCalled_thenExceptionShouldBeThrown() {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(CityNotFoundException.class, () -> {
            cityService.updateCity(1L, HelperMethods.buildCity("İstanbul", "34"));
        });
    }

    @Test
    public void givenCityIdExistingInDB_whenDeleteCityIsCalled_thenCityShouldBeDeleted() {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(new City()));
        assertDoesNotThrow(() -> {
            cityService.deleteCity(anyLong());
        });
    }

    @Test
    public void givenCityIdNonExistingInDB_whenDeleteCityIsCalled_thenExceptionShouldBeThrown() {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(CityNotFoundException.class, () -> {
            cityService.deleteCity(anyLong());
        });
    }

}
