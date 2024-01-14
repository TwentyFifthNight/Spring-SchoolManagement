package com.school.schoolManagement.Service;

import com.school.schoolManagement.Dto.AddressDto;
import com.school.schoolManagement.Dto.Request.Address.CreateAddressRequest;
import com.school.schoolManagement.Dto.Request.Address.UpdateAddressRequest;
import com.school.schoolManagement.Model.Address;

import java.util.List;

public interface AddressService {
    void createAddress(CreateAddressRequest request);

    void updateAddress(Long id, UpdateAddressRequest request);

    void deleteAddress(Long id);

    AddressDto findAddressById(Long id);
    Address findAddressByAddressId(Long id);

    List<AddressDto> findAllAddresses();
}
