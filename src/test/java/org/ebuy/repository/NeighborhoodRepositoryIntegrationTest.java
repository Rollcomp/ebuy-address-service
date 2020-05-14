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

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class NeighborhoodRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private NeighborhoodRepository neighborhoodRepository;

    @BeforeEach
    public void init() {
        City city = HelperMethods.buildCity("İstanbul", "34");
        District district = HelperMethods.buildDistrict("Kadıköy");
        city.addDistrict(district);
        Neighborhood neighborhood = HelperMethods.buildNeighborhood("Acıbadem", "34718");
        district.addNeighborhood(neighborhood);
        entityManager.persistAndFlush(city);

        city = HelperMethods.buildCity("Ankara", "06");
        district = HelperMethods.buildDistrict("Çankaya");
        city.addDistrict(district);
        entityManager.persistAndFlush(city);
    }

    @Test
    public void givenNeighborhoodId_whenFindByIdIsCalled_thenShouldReturnNonEmptyNeighborhood() {
        Optional<Neighborhood> neighborhoodOptional = neighborhoodRepository.findById(1L);
        assertThat(neighborhoodOptional.isPresent()).isTrue();
        assertThat(neighborhoodOptional.get().getName()).isEqualTo("Acıbadem");
        assertThat(neighborhoodOptional.get().getPostCode()).isEqualTo("34718");
    }

    @Test
    public void givenNewNeighborhoodValue_whenSaveIsCalled_thenShouldBeSaved() {
        Optional<District> districtOptional = districtRepository.findById(1L);
        assertThat(districtOptional.isPresent()).isTrue();

        Neighborhood newNeighborhood = HelperMethods.buildNeighborhood("Bostancı", "34744");
        District district = districtOptional.get();
        district.addNeighborhood(newNeighborhood);
        entityManager.persist(newNeighborhood);
        entityManager.flush();

        Optional<Neighborhood> neighborhoodOptional = neighborhoodRepository.findById(newNeighborhood.getId());
        assertThat(neighborhoodOptional.isPresent()).isTrue();
    }

    @Test
    public void givenNeighborhoodId_whenDeleteIsCalled_thenShouldBeDeleted() {
        Optional<Neighborhood> neighborhoodOptional = neighborhoodRepository.findById(1L);
        assertThat(neighborhoodOptional.isPresent()).isTrue();
        Neighborhood neighborhood = neighborhoodOptional.get();
        neighborhood.getDistrict().removeNeighborhood(neighborhood);
        entityManager.remove(neighborhood);
        entityManager.flush();

        neighborhoodOptional = neighborhoodRepository.findById(1L);
        assertThat(neighborhoodOptional.isPresent()).isFalse();
    }

    @Test
    public void givenUpdatedValuesForNeighborhood_whenIsUpdated_thenShouldBeUpdated() {
        Optional<Neighborhood> neighborhoodOptional = neighborhoodRepository.findById(1L);
        assertThat(neighborhoodOptional.isPresent()).isTrue();
        Neighborhood neighborhood = neighborhoodOptional.get();
        neighborhood.setName("Çankaya");
        neighborhood.setPostCode("00000");
        entityManager.persistAndFlush(neighborhood);

        neighborhoodOptional = neighborhoodRepository.findById(1L);
        assertThat(neighborhoodOptional.isPresent()).isTrue();
        assertThat(neighborhoodOptional.get().getName()).isEqualTo("Çankaya");
        assertThat(neighborhoodOptional.get().getPostCode()).isEqualTo("00000");
    }

    @Test
    public void givenDistrictId_whenFindNeighborhoodsByDistrictId_thenShouldBeReturned() {
        Set<Neighborhood> result = neighborhoodRepository.findNeighborhoodsByDistrictId(1L);
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }

}
