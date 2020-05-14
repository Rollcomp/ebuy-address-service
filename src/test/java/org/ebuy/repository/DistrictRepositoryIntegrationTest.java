package org.ebuy.repository;

import org.ebuy.helper.HelperMethods;
import org.ebuy.model.City;
import org.ebuy.model.District;
import org.ebuy.model.Neighborhood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DistrictRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private NeighborhoodRepository neighborhoodRepository;

    @BeforeEach
    public void init() {
        City city = HelperMethods.buildCity("İstanbul", "34");
        List<District> districts = HelperMethods.buildDistricts("Kadıköy");
        districts.forEach(city::addDistrict);
        entityManager.persistAndFlush(city);

        city = HelperMethods.buildCity("Ankara", "06");
        districts = HelperMethods.buildDistricts("Çankaya", "Mamak");
        districts.forEach(city::addDistrict);
        entityManager.persistAndFlush(city);
    }

    @Test
    public void givenDistrictId_whenFindByIdIsCalled_thenShouldReturnNonEmptyDistrict() {
        Optional<District> districtOptional = districtRepository.findById(1L);
        assertThat(districtOptional.isPresent()).isTrue();
        assertThat(districtOptional.get().getName()).isEqualTo("Kadıköy");
    }

    @Test
    public void givenNewDistrictValue_whenSaveIsCalled_thenShouldBeSaved() {
        Optional<City> cityOptional = cityRepository.findById(1L);
        assertThat(cityOptional.isPresent()).isTrue();

        District newDistrict = HelperMethods.buildDistrict("Kartal");
        City city = cityOptional.get();
        city.addDistrict(newDistrict);
        entityManager.persist(newDistrict);
        entityManager.flush();

        Optional<District> districtOptional = districtRepository.findById(newDistrict.getId());
        assertThat(districtOptional.isPresent()).isTrue();
        assertThat(districtOptional.get().getName()).isEqualTo("Kartal");
    }

    @Test
    public void givenDistrictId_whenDeleteIsCalled_thenShouldBeDeleted() {
        Optional<District> districtOptional = districtRepository.findById(1L);
        assertThat(districtOptional.isPresent()).isTrue();
        District district = districtOptional.get();
        district.getCity().removeDistrict(district);
        entityManager.remove(district);
        entityManager.flush();

        districtOptional = districtRepository.findById(1L);
        assertThat(districtOptional.isPresent()).isFalse();
    }

    @Test
    public void givenUpdatedValuesForDistrict_whenIsUpdated_thenShouldBeUpdated() {
        Optional<District> districtOptional = districtRepository.findById(1L);
        assertThat(districtOptional.isPresent()).isTrue();
        District district = districtOptional.get();
        district.setName("Kartal");
        entityManager.persistAndFlush(district);

        districtOptional = districtRepository.findById(1L);
        assertThat(districtOptional.isPresent()).isTrue();
        assertThat(districtOptional.get().getName()).isEqualTo("Kartal");
    }

    @Test
    public void givenNeighborhoodValues_whenAreAddedToDistrict_thenShouldBeAddedAsSuccessful() {
        Optional<District> districtOptional = districtRepository.findById(1L);
        assertThat(districtOptional.isPresent()).isTrue();
        District district = districtOptional.get();

        Neighborhood neighborhood = HelperMethods.buildNeighborhood("Acıbadem", "34718");
        district.addNeighborhood(neighborhood);
        neighborhood = HelperMethods.buildNeighborhood("Bostancı", "34744");
        district.addNeighborhood(neighborhood);
        entityManager.persistAndFlush(district);

        districtOptional = districtRepository.findById(1L);
        assertThat(districtOptional.isPresent()).isTrue();
        district = districtOptional.get();
        assertThat(district.getNeighborhoods()).hasSize(2);
    }

    @Test
    public void givenDistrict_whenIsRemovedFromCity_thenShouldBeRemovedAsSuccessful() {
        Optional<District> districtOptional = districtRepository.findById(1L);
        assertThat(districtOptional.isPresent()).isTrue();
        District district = districtOptional.get();

        Neighborhood neighborhood = HelperMethods.buildNeighborhood("Acıbadem", "34718");
        district.addNeighborhood(neighborhood);
        neighborhood = HelperMethods.buildNeighborhood("Bostancı", "34744");
        district.addNeighborhood(neighborhood);
        entityManager.persistAndFlush(district);

        district.removeNeighborhood(neighborhood);
        entityManager.persistAndFlush(district);

        districtOptional = districtRepository.findById(1L);
        assertThat(districtOptional.isPresent()).isTrue();
        district = districtOptional.get();
        assertThat(district.getNeighborhoods()).hasSize(1);

        Optional<Neighborhood> neighborhoodOptional = neighborhoodRepository.findById(neighborhood.getId());
        assertThat(neighborhoodOptional.isPresent()).isFalse();
    }

    @Test
    public void givenCityId_whenFindDistrictsById_thenShouldBeReturned() {
        Optional<City> cityOptional = cityRepository.findById(1L);
        assertThat(cityOptional.isPresent()).isTrue();
        City city = cityOptional.get();

        List<District> districts = HelperMethods.buildDistricts("Beşiktaş", "Kartal");
        districts.forEach(city::addDistrict);
        entityManager.persistAndFlush(city);

        Set<District> result = districtRepository.findDistrictsByCityId(1L);
        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
    }

}
