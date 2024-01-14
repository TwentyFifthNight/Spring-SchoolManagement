package com.school.schoolManagement.Service;

import com.school.schoolManagement.Dto.AddressDto;
import com.school.schoolManagement.Dto.Converter.AddressDtoConverter;
import com.school.schoolManagement.Dto.Request.Address.CreateAddressRequest;
import com.school.schoolManagement.Dto.Request.Address.UpdateAddressRequest;
import com.school.schoolManagement.Exception.Address.AddressNotFoundException;
import com.school.schoolManagement.Helper.BusinessMessage;
import com.school.schoolManagement.Model.Address;
import com.school.schoolManagement.Repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService{
    private final AddressRepository addressRepository;
    private final AddressDtoConverter converter;
    private final ResourceBundle LogMessage;

    public AddressServiceImpl(AddressRepository addressRepository,
                          AddressDtoConverter converter) {
        this.addressRepository = addressRepository;
        this.converter = converter;

        LogMessage = ResourceBundle.getBundle("i18n/AddressLogMessage", Locale.getDefault());
    }

    public void createAddress(CreateAddressRequest request) throws AddressNotFoundException{
        Address address = new Address();
        address.setHouseNumber(request.getHouseNumber());
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setZipCode(request.getZipCode());

        addressRepository.save(address);
        log.info(LogMessage.getString("Created"));
    }

    public void updateAddress(Long id, UpdateAddressRequest request) throws AddressNotFoundException{
        Address address = findAddressByAddressId(id);

        address.setHouseNumber(request.getHouseNumber());
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setZipCode(request.getZipCode());

        addressRepository.save(address);
        log.info(MessageFormat.format(LogMessage.getString("Updated"), id));
    }

    public void deleteAddress(Long id) throws AddressNotFoundException{
        Address address = findAddressByAddressId(id);

        addressRepository.delete(address);
        log.info(MessageFormat.format(LogMessage.getString("Deleted"), id));
    }

    public AddressDto findAddressById(Long id) throws AddressNotFoundException{
        Address address = findAddressByAddressId(id);

        log.info(MessageFormat.format(LogMessage.getString("Found"), id));
        return converter.convert(address);
    }

    public List<AddressDto> findAllAddresses() throws AddressNotFoundException{
        List<Address> addressList = addressRepository.findAll();

        if (addressList.isEmpty()) {
            log.error(LogMessage.getString("ListEmpty"));
            throw new AddressNotFoundException(BusinessMessage.Address.ADDRESS_LIST_EMPTY);
        }

        log.info(LogMessage.getString("Listed"));
        return converter.convert(addressList);
    }


    public Address findAddressByAddressId(Long id) throws AddressNotFoundException{
        return addressRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(MessageFormat.format(LogMessage.getString("NotFound"), id));
                    throw new AddressNotFoundException(BusinessMessage.Address.ADDRESS_NOT_FOUND);
                });
    }
}
