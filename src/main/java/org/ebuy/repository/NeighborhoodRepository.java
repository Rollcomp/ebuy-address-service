package org.ebuy.repository;

import org.ebuy.model.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Burak Köken on 10.5.2020.
 */
@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {

    List<Neighborhood> findNeighborhoodsByDistrictId(Long districtId);

}
