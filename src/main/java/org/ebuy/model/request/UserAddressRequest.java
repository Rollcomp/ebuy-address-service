package org.ebuy.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Burak Köken on 10.5.2020.
 */
@Getter
@Setter
public class UserAddressRequest {

    private String title;
    private String detail;
    private Long cityId;
    private Long districtId;
    private Long neighborhoodId;
    private String userId;

}
