package org.ebuy.helper;

import org.ebuy.model.City;
import org.ebuy.model.District;
import org.ebuy.model.Neighborhood;
import org.ebuy.model.UserAddress;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelperMethods {

    public static City buildCity(String name, String code) {
        City city = new City();
        city.setName(name);
        city.setCode(code);
        city.setCountryCode("TR");
        return city;
    }

    public static City buildCity(Long id, String name, String code) {
        City city = buildCity(name, code);
        city.setId(id);
        return city;
    }

    public static District buildDistrict(String name) {
        District district = new District();
        district.setName(name);
        return district;
    }

    public static List<District> buildDistricts(String ...districts) {
        return Arrays.stream(districts)
                .map(HelperMethods::buildDistrict)
                .collect(Collectors.toList());
    }

    public static Neighborhood buildNeighborhood(String name, String postCode) {
        Neighborhood neighborhood = new Neighborhood();
        neighborhood.setName(name);
        neighborhood.setPostCode(postCode);
        return neighborhood;
    }

    public static UserAddress buildUserAddress(String userId, String title, String detail) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setTitle(title);
        userAddress.setDetail(detail);
        return userAddress;
    }

}
