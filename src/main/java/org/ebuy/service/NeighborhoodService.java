package org.ebuy.service;

import org.ebuy.exception.NeighborhoodNotFoundException;
import org.ebuy.model.Neighborhood;
import org.ebuy.repository.NeighborhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NeighborhoodService {

    private NeighborhoodRepository neighborhoodRepository;

    @Autowired
    public NeighborhoodService(NeighborhoodRepository neighborhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
    }


    public List<Neighborhood> findAllNeighborhoods() {
        return neighborhoodRepository.findAll();
    }

    public Neighborhood findNeighborhoodById(Long id) {
        Optional<Neighborhood> neighborhoodOptional = neighborhoodRepository.findById(id);
        return neighborhoodOptional.orElseThrow(() -> new NeighborhoodNotFoundException(id));
    }

    public Neighborhood createNeighborhood(Neighborhood neighborhood) {
        return neighborhoodRepository.save(neighborhood);
    }

    public List<Neighborhood> createBulkNeighborhoods(List<Neighborhood> neighborhoods) {
        return neighborhoodRepository.saveAll(neighborhoods);
    }

    public Neighborhood updateNeighborhood(Long neighborhoodId, Neighborhood updatedNeighborhood) {
        Optional<Neighborhood> neighborhoodOptional = neighborhoodRepository.findById(neighborhoodId);
        Neighborhood neighborhood = neighborhoodOptional.orElseThrow(() -> new NeighborhoodNotFoundException(neighborhoodId));
        neighborhood.setName(updatedNeighborhood.getName());
        neighborhood.setPostCode(updatedNeighborhood.getPostCode());
        return neighborhoodRepository.save(neighborhood);
    }

    public void deleteNeighborhood(Long neighborhoodId) {
        Optional<Neighborhood> neighborhoodOptional = neighborhoodRepository.findById(neighborhoodId);
        Neighborhood neighborhood = neighborhoodOptional.orElseThrow(() -> new NeighborhoodNotFoundException(neighborhoodId));
        neighborhoodRepository.delete(neighborhood);
    }

    public List<Neighborhood> findNeighborhoods(Long districtId) {
        return neighborhoodRepository.findNeighborhoodsByDistrictId(districtId);
    }

}
