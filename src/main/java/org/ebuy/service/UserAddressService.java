package org.ebuy.service;

import org.ebuy.exception.UserAddressNotFoundException;
import org.ebuy.model.UserAddress;
import org.ebuy.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserAddressService {

    private UserAddressRepository userAddressRepository;

    @Autowired
    public UserAddressService(UserAddressRepository userAddressRepository) {
        this.userAddressRepository = userAddressRepository;
    }

    public List<UserAddress> findAllUserAddresses() {
        return userAddressRepository.findAll();
    }

    public UserAddress findUserAddressById(Long id) {
        Optional<UserAddress> userAddressOptional = userAddressRepository.findById(id);
        return userAddressOptional.orElseThrow(() -> new UserAddressNotFoundException(id));
    }

    public UserAddress createNeighborhood(UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }

    public UserAddress updateUserAddress(Long userAddressId, UserAddress updateUserAddress) {
        Optional<UserAddress> userAddressOptional = userAddressRepository.findById(userAddressId);
        UserAddress userAddress = userAddressOptional.orElseThrow(() -> new UserAddressNotFoundException(userAddressId));
        userAddress.setTitle(updateUserAddress.getTitle());
        userAddress.setDetail(updateUserAddress.getDetail());
        return userAddressRepository.save(userAddress);
    }

    public void deleteNeighborhood(Long userAddressId) {
        Optional<UserAddress> userAddressOptional = userAddressRepository.findById(userAddressId);
        UserAddress neighborhood = userAddressOptional.orElseThrow(() -> new UserAddressNotFoundException(userAddressId));
        userAddressRepository.delete(neighborhood);
    }

    public List<UserAddress> findUserAddressesByUserId(String userId) {
        return userAddressRepository.findUserAddressesByUserId(userId);
    }

}
