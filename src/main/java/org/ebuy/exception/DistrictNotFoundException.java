package org.ebuy.exception;

public class DistrictNotFoundException extends NotFoundException {

    public DistrictNotFoundException(Long districtId) {
        super("District not found with district id : " + districtId);
    }

}
