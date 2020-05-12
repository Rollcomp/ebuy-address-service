package org.ebuy.repository;

import org.ebuy.helper.HelperMethods;
import org.ebuy.model.City;
import org.ebuy.model.District;
import org.ebuy.model.Neighborhood;
import org.ebuy.model.UserAddress;
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

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserAddressRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @BeforeEach
    public void init() {
        City city = HelperMethods.buildCity("İstanbul", "34");

        District district = HelperMethods.buildDistrict("Kadıköy");
        city.addDistrict(district);

        Neighborhood neighborhood = HelperMethods.buildNeighborhood("Acıbadem", "34718");
        district.addNeighborhood(neighborhood);
        entityManager.persistAndFlush(city);

        UserAddress userAddress = HelperMethods.buildUserAddress("1", "Ev Adresi", "EBuy Apartmanı Kat:3");
        userAddress.setCity(city);
        userAddress.setDistrict(district);
        userAddress.setNeighborhood(neighborhood);
        entityManager.persistAndFlush(userAddress);
    }

    @Test
    public void givenUserAddressId_whenFindByIdIsCalled_thenShouldReturnNonEmptyUserAddress() {
        Optional<UserAddress> userAddressOptional = userAddressRepository.findById(1L);
        assertThat(userAddressOptional.isPresent()).isTrue();
        UserAddress userAddress = userAddressOptional.get();
        assertThat(userAddress.getId()).isEqualTo(1L);
        assertThat(userAddress.getUserId()).isEqualTo("1");
        assertThat(userAddress.getTitle()).isEqualTo("Ev Adresi");
        assertThat(userAddress.getDetail()).isEqualTo("EBuy Apartmanı Kat:3");
        assertThat(userAddress.getCity().getId()).isEqualTo(1L);
        assertThat(userAddress.getDistrict().getId()).isEqualTo(1L);
        assertThat(userAddress.getNeighborhood().getId()).isEqualTo(1L);
    }

    @Test
    public void givenNewUserAddressValue_whenSaveIsCalled_thenShouldBeSaved() {
        Optional<City> cityOptional = cityRepository.findById(1L);
        assertThat(cityOptional.isPresent()).isTrue();
        Optional<District> districtOptional = cityOptional.get().getDistricts().stream().findFirst();
        assertThat(districtOptional.isPresent()).isTrue();
        Optional<Neighborhood> neighborhoodOptional = districtOptional.get().getNeighborhoods().stream().findFirst();
        assertThat(neighborhoodOptional.isPresent()).isTrue();

        UserAddress userAddress = HelperMethods.buildUserAddress("1", "İş Adresi", "EBuy Apartmanı Kat:3");
        userAddress.setCity(cityOptional.get());
        userAddress.setDistrict(districtOptional.get());
        userAddress.setNeighborhood(neighborhoodOptional.get());
        entityManager.persistAndFlush(userAddress);

        Optional<UserAddress> userAddressOptional = userAddressRepository.findById(2L);
        assertThat(userAddressOptional.isPresent()).isTrue();

        List<UserAddress> userAddressList = userAddressRepository.findUserAddressesByUserId("1");
        assertThat(userAddressList).hasSize(2);
    }

    @Test
    public void givenUserAddressId_whenDeleteIsCalled_thenShouldBeDeleted() {
        Optional<UserAddress> userAddressOptional = userAddressRepository.findById(1L);
        assertThat(userAddressOptional.isPresent()).isTrue();
        UserAddress userAddress = userAddressOptional.get();
        entityManager.remove(userAddress);
        entityManager.flush();

        userAddressOptional = userAddressRepository.findById(1L);
        assertThat(userAddressOptional.isPresent()).isFalse();

        List<UserAddress> userAddressList = userAddressRepository.findUserAddressesByUserId("1");
        assertThat(userAddressList).hasSize(0);
    }

    @Test
    public void givenUpdatedValuesForUserAddress_whenIsUpdated_thenShouldBeUpdated() {
        Optional<UserAddress> userAddressOptional = userAddressRepository.findById(1L);
        assertThat(userAddressOptional.isPresent()).isTrue();
        UserAddress userAddress = userAddressOptional.get();
        userAddress.setTitle("Yeni İş Adresi");
        userAddress.setDetail("Test");
        entityManager.persistAndFlush(userAddress);

        userAddressOptional = userAddressRepository.findById(1L);
        assertThat(userAddressOptional.isPresent()).isTrue();
        userAddress = userAddressOptional.get();
        assertThat(userAddress.getTitle()).isEqualTo("Yeni İş Adresi");
        assertThat(userAddress.getDetail()).isEqualTo("Test");
    }

}
