package org.ebuy.repository;

import org.ebuy.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Burak Köken on 10.5.2020.
 */
@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    List<UserAddress> findUserAddressesByUserId(String userId);

}
