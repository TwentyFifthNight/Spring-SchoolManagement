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
    private final ResourceBundle addressLogMessage;

    public AddressServiceImpl(AddressRepository addressRepository,
                          AddressDtoConverter converter) {
        this.addressRepository = addressRepository;
        this.converter = converter;

        addressLogMessage = ResourceBundle.getBundle("i18n/AddressLogMessage", Locale.getDefault());
    }

    public void createAddress(CreateAddressRequest request) throws AddressNotFoundException{
        Address address = new Address();
        address.setHouseNumber(request.getHouseNumber());
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setZipCode(request.getZipCode());

        addressRepository.save(address);
        log.info(addressLogMessage.getString("addressCreated"));
    }

    public void updateAddress(Long id, UpdateAddressRequest request) throws AddressNotFoundException{
        Address address = findAddressByAddressId(id);

        address.setHouseNumber(request.getHouseNumber());
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setZipCode(request.getZipCode());

        addressRepository.save(address);
        log.info(MessageFormat.format(addressLogMessage.getString("addressUpdated"), id));
    }

    public void deleteAddress(Long id) throws AddressNotFoundException{
        Address address = findAddressByAddressId(id);

        addressRepository.delete(address);
        log.info(MessageFormat.format(addressLogMessage.getString("addressDeleted"), id));
    }

    public AddressDto findAddressById(Long id) throws AddressNotFoundException{
        Address address = findAddressByAddressId(id);

        log.info(MessageFormat.format(addressLogMessage.getString("addressFound"), id));
        return converter.convert(address);
    }

    public List<AddressDto> findAllAddresses() throws AddressNotFoundException{
        List<Address> addressList = addressRepository.findAll();

        if (addressList.isEmpty()) {
            log.error(addressLogMessage.getString("addressListEmpty"));
            throw new AddressNotFoundException(BusinessMessage.Address.ADDRESS_LIST_EMPTY);
        }

        log.info(addressLogMessage.getString("addressListed"));
        return converter.convert(addressList);
    }


    protected Address findAddressByAddressId(Long id) throws AddressNotFoundException{
        return addressRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(MessageFormat.format(addressLogMessage.getString("addressNotFound"), id));
                    throw new AddressNotFoundException(BusinessMessage.Address.ADDRESS_NOT_FOUND);
                });
    }
}
