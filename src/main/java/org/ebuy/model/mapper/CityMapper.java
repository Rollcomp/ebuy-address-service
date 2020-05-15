package org.ebuy.model.mapper;

import org.ebuy.model.City;
import org.ebuy.model.request.CityRequest;
import org.ebuy.model.response.CityDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityDto toCityDto(City city);

    List<CityDto> toCityDtoList(List<City> city);

    City toCity(CityRequest request);

    List<City> toCityList(List<CityRequest> cityRequestList);

}
