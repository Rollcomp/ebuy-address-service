package org.ebuy.model.mapper;

import org.ebuy.model.District;
import org.ebuy.model.request.DistrictRequest;
import org.ebuy.model.response.DistrictDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DistrictMapper {

    DistrictDto toDistrictDto(District district);

    List<DistrictDto> toDistrictDtoList(List<District> districtList);

    District toDistrict(DistrictRequest request);

    List<District> toDistrictList(List<DistrictRequest> request);

}
