package org.ebuy.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Burak Köken on 9.5.2020.
 */
@Getter
@Setter
public class NeighborhoodRequest {

    private String name;
    private String postCode;
    private Long districtId;

}
