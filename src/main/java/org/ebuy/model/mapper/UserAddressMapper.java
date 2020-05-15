package org.ebuy.model.mapper;

import org.ebuy.model.UserAddress;
import org.ebuy.model.request.UserAddressRequest;
import org.ebuy.model.response.UserAddressDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAddressMapper {

    UserAddressDto toUserAddressDto(UserAddress userAddress);

    List<UserAddressDto> toUserAddressDtoList(List<UserAddress> userAddressList);

    UserAddress toUserAddress(UserAddressRequest request);

    List<UserAddress> toUserAddressList(List<UserAddressRequest> request);

}
