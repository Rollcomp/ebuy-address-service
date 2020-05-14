package org.ebuy.model.mapper;

import org.ebuy.model.Neighborhood;
import org.ebuy.model.response.NeighborhoodDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NeighborhoodMapper {

    List<NeighborhoodDto> toNeighborhoodDtoList(List<Neighborhood> neighborhoodList);

}
