package com.school.schoolManagement.Service;

import com.school.schoolManagement.Dto.AddressDto;
import com.school.schoolManagement.Dto.Request.Address.BaseAddressRequest;

import java.util.List;

public interface AddressService {
    void createAddress(BaseAddressRequest request);

    void updateAddress(Long id, BaseAddressRequest request);

    void deleteAddress(Long id);

    AddressDto findAddressById(Long id);

    List<AddressDto> findAllAddresses();
}
