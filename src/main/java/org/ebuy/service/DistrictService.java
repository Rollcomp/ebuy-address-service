package org.ebuy.service;

import org.ebuy.exception.DistrictNotFoundException;
import org.ebuy.model.District;
import org.ebuy.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DistrictService {

    private DistrictRepository districtRepository;

    @Autowired
    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public List<District> findAllDistricts() {
        return districtRepository.findAll();
    }

    public District findDistrictById(Long id) {
        Optional<District> districtOptional = districtRepository.findById(id);
        return districtOptional.orElseThrow(() -> new DistrictNotFoundException(id));
    }

    public District createDistrict(District district) {
        return districtRepository.save(district);
    }

    public List<District> createBulkDistrict(List<District> districts) {
        return districtRepository.saveAll(districts);
    }

    public District updateDistrict(Long districtId, District updatedDistrict) {
        Optional<District> districtOptional = districtRepository.findById(districtId);
        District district = districtOptional.orElseThrow(() -> new DistrictNotFoundException(districtId));
        district.setName(updatedDistrict.getName());
        return districtRepository.save(district);
    }

    public void deleteDistrict(Long districtId) {
        Optional<District> districtOptional = districtRepository.findById(districtId);
        District district = districtOptional.orElseThrow(() -> new DistrictNotFoundException(districtId));
        districtRepository.delete(district);
    }

    public List<District> findDistricts(Long cityId) {
        return districtRepository.findDistrictsByCityId(cityId);
    }

}
