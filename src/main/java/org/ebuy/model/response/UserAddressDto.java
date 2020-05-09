package org.ebuy.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Burak Köken on 10.5.2020.
 */
@Getter
@Setter
public class UserAddressDto {

    private Long id;
    private String title;
    private String detail;
    private CityDto city;
    private DistrictDto district;
    private NeighborhoodDto neighborhood;

}
