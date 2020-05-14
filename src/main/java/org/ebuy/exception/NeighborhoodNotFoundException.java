package org.ebuy.exception;

public class NeighborhoodNotFoundException extends NotFoundException {

    public NeighborhoodNotFoundException(Long neighborhoodId) {
        super("Neighborhood not found with neighborhood id : " + neighborhoodId);
    }

}
