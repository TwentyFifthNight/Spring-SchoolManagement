package com.school.schoolManagement.Dto.Converter;

import com.school.schoolManagement.Dto.AddressDto;
import com.school.schoolManagement.Model.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressDtoConverter {
    public AddressDto convert(Address from){
        return new AddressDto(
                from.getId(),
                from.getHouseNumber(),
                from.getStreet(),
                from.getCity(),
                from.getZipCode()
        );
    }

    public List<AddressDto> convert(List<Address> from){
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}
