package org.ebuy.model.mapper;

import org.ebuy.model.Neighborhood;
import org.ebuy.model.request.NeighborhoodRequest;
import org.ebuy.model.response.NeighborhoodDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NeighborhoodMapper {

    NeighborhoodDto toNeighborhoodDto(Neighborhood neighborhood);

    List<NeighborhoodDto> toNeighborhoodDtoList(List<Neighborhood> neighborhoodList);

    Neighborhood toNeighborhood(NeighborhoodRequest request);

    List<Neighborhood> toNeighborhoodList(List<NeighborhoodRequest> request);

}
