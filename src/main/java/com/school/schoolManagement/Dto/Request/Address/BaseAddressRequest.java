package com.school.schoolManagement.Dto.Request.Address;

import com.school.schoolManagement.Helper.ValidationMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseAddressRequest {
    @NotNull(message = ValidationMessage.Address.HOUSE_NUMBER_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Address.HOUSE_NUMBER_NOT_EMPTY)
    private String houseNumber;

    @NotNull(message = ValidationMessage.Address.STREET_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Address.STREET_NOT_EMPTY)
    private String street;

    @NotNull(message = ValidationMessage.Address.CITY_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Address.CITY_NOT_EMPTY)
    private String city;

    @NotNull(message = ValidationMessage.Address.ZIPCODE_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Address.ZIPCODE_NOT_EMPTY)
    private String zipCode;
}
