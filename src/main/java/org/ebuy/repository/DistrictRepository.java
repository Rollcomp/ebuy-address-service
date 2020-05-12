package org.ebuy.repository;

import org.ebuy.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Burak Köken on 10.5.2020.
 */
@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

}
