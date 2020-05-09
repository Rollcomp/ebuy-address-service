package org.ebuy.controller;

import org.ebuy.model.request.UserAddressRequest;
import org.ebuy.model.response.UserAddressDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Burak Köken on 10.5.2020.
 */
@RestController
@RequestMapping("/address")
public class UserAddressController {

    @GetMapping
    public ResponseEntity<List<UserAddressDto>> getUserAddresses() {
        return null;
    }


    @GetMapping("/{addressId}")
    public ResponseEntity<UserAddressDto> getUserAddress(@PathVariable Long addressId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<UserAddressDto> createUserAddress(@RequestBody UserAddressRequest request) {
        return null;
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<UserAddressDto> updateUserAddress(@PathVariable Long addressId, @RequestBody UserAddressRequest request) {
        return null;
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteUserAddress(@PathVariable Long addressId) {
        return null;
    }

}
