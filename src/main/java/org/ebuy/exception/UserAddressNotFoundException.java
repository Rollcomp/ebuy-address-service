package org.ebuy.exception;

public class UserAddressNotFoundException extends NotFoundException {

    public UserAddressNotFoundException(Long userAddressId) {
        super("User Address not found with userAddress id : " + userAddressId);
    }

}
