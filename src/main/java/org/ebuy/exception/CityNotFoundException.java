package org.ebuy.exception;

public class CityNotFoundException extends NotFoundException {

    public CityNotFoundException(Long cityId) {
        super("City not found with city id : " + cityId);
    }

}
