package org.ebuy.controller;

import org.ebuy.model.UserAddress;
import org.ebuy.model.mapper.UserAddressMapper;
import org.ebuy.model.request.UserAddressRequest;
import org.ebuy.model.response.UserAddressDto;
import org.ebuy.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Burak Köken on 10.5.2020.
 */
@RestController
@RequestMapping("/address")
public class UserAddressController {

    private UserAddressService userAddressService;
    private UserAddressMapper userAddressMapper;

    @Autowired
    public UserAddressController(UserAddressService userAddressService,
                                 UserAddressMapper userAddressMapper) {
        this.userAddressService = userAddressService;
        this.userAddressMapper = userAddressMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserAddressDto>> getUserAddresses() {
        List<UserAddress> userAddresses = userAddressService.findUserAddressesByUserId(null);
        return ResponseEntity.ok(userAddressMapper.toUserAddressDtoList(userAddresses));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<UserAddressDto> getUserAddress(@PathVariable Long addressId) {
        UserAddress userAddress = userAddressService.findUserAddressById(addressId);
        return ResponseEntity.ok(userAddressMapper.toUserAddressDto(userAddress));
    }

    @PostMapping
    public ResponseEntity<UserAddressDto> createUserAddress(@RequestBody UserAddressRequest request) {
        UserAddress savedNeighborhood = userAddressService.createUserAddress(userAddressMapper.toUserAddress(request));
        return ResponseEntity.ok(userAddressMapper.toUserAddressDto(savedNeighborhood));

    }

    @PutMapping("/{addressId}")
    public ResponseEntity<UserAddressDto> updateUserAddress(@PathVariable Long addressId, @RequestBody UserAddressRequest request) {
        UserAddress updatedUserAddress = userAddressMapper.toUserAddress(request);
        UserAddress result = userAddressService.updateUserAddress(addressId, updatedUserAddress);
        return ResponseEntity.ok(userAddressMapper.toUserAddressDto(result));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteUserAddress(@PathVariable Long addressId) {
        userAddressService.deleteNeighborhood(addressId);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

}
