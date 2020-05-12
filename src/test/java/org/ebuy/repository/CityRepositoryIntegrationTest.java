package org.ebuy.repository;

import org.ebuy.helper.HelperMethods;
import org.ebuy.model.City;
import org.ebuy.model.District;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by Burak Köken on 10.5.2020.
 */
@DataJpaTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CityRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @BeforeEach
    public void init() {
        List<City> cities = new ArrayList<>();
        cities.add(HelperMethods.buildCity("İstanbul", "34"));
        cities.add(HelperMethods.buildCity("Ankara", "06"));
        cities.forEach(city -> entityManager.persist(city));
        entityManager.flush();
    }

    @Test
    public void givenCityId_whenFindByIdIsCalled_thenShouldReturnNonEmptyCity() {
        Optional<City> cityOptional = cityRepository.findById(1L);
        assertThat(cityOptional.isPresent()).isTrue();
        assertThat(cityOptional.get().getName()).isEqualTo("İstanbul");
        assertThat(cityOptional.get().getCode()).isEqualTo("34");
    }

    @Test
    public void givenNewCityValues_whenSaveIsCalled_thenShouldBeSaved() {
        City newCity = HelperMethods.buildCity("Kocaeli", "41");
        entityManager.persist(newCity);
        entityManager.flush();

        Optional<City> city = cityRepository.findById(newCity.getId());
        assertThat(city.isPresent()).isTrue();
        assertThat(city.get().getName()).isEqualTo("Kocaeli");
        assertThat(city.get().getCode()).isEqualTo("41");
    }

    @Test
    public void givenCityId_whenDeleteIsCalled_thenShouldBeDeleted() {
        Optional<City> cityOptional = cityRepository.findById(1L);
        assertThat(cityOptional.isPresent()).isTrue();
        City city = cityOptional.get();
        entityManager.remove(city);
        entityManager.flush();

        cityOptional = cityRepository.findById(1L);
        assertThat(cityOptional.isPresent()).isFalse();
    }

    @Test
    public void givenUpdatedValuesForCity_whenIsUpdated_thenShouldBeUpdated() {
        Optional<City> cityOptional = cityRepository.findById(1L);
        assertThat(cityOptional.isPresent()).isTrue();
        City city = cityOptional.get();
        city.setName("İzmir");
        city.setCode("35");
        entityManager.persistAndFlush(city);

        cityOptional = cityRepository.findById(1L);
        assertThat(cityOptional.isPresent()).isTrue();
        assertThat(cityOptional.get().getName()).isEqualTo("İzmir");
        assertThat(cityOptional.get().getCode()).isEqualTo("35");
    }

    @Test
    public void givenDistrictValues_whenAreAddedToCity_thenShouldBeAddedAsSuccessful() {
        Optional<City> cityOptional = cityRepository.findById(1L);
        assertThat(cityOptional.isPresent()).isTrue();
        City city = cityOptional.get();

        List<District> districts = HelperMethods.buildDistricts("Kadıköy", "Beşiktaş", "Kartal");
        districts.forEach(city::addDistrict);
        entityManager.persistAndFlush(city);
        city.getDistricts().forEach(district -> assertThat(district.getId()).isNotNull());

        cityOptional = cityRepository.findById(city.getId());
        assertThat(cityOptional.isPresent()).isTrue();
        city = cityOptional.get();
        assertThat(city.getDistricts()).hasSize(3);
    }

    @Test
    public void givenDistrict_whenIsRemovedFromCity_thenShouldBeRemovedAsSuccessful() {
        Optional<City> cityOptional = cityRepository.findById(1L);
        assertThat(cityOptional.isPresent()).isTrue();
        City city = cityOptional.get();

        List<District> districts = HelperMethods.buildDistricts("Kadıköy", "Beşiktaş", "Kartal");
        districts.forEach(city::addDistrict);
        entityManager.persistAndFlush(city);

        Optional<District> district = city.getDistricts().stream().findFirst();
        district.ifPresent(city::removeDistrict);
        entityManager.persistAndFlush(city);

        cityOptional = cityRepository.findById(city.getId());
        assertThat(cityOptional.isPresent()).isTrue();
        city = cityOptional.get();
        assertThat(city.getDistricts()).hasSize(2);

        Optional<District> districtOptional = districtRepository.findById(1L);
        assertThat(districtOptional.isPresent()).isFalse();
    }

}
