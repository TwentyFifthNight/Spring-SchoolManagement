package com.school.schoolManagement.Controller;

import com.school.schoolManagement.Dto.AddressDto;
import com.school.schoolManagement.Dto.Request.Address.CreateAddressRequest;
import com.school.schoolManagement.Dto.Request.Address.UpdateAddressRequest;
import com.school.schoolManagement.Service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Void> createAddress(@RequestBody @Valid CreateAddressRequest request) {
        System.out.println(request);
        addressService.createAddress(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable Long id,
                                              @RequestBody @Valid UpdateAddressRequest request) {
        addressService.updateAddress(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> findAddressById(@PathVariable Long id) {
        return new ResponseEntity<>(addressService.findAddressById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> findAllAddresses() {
        return new ResponseEntity<>(addressService.findAllAddresses(), HttpStatus.OK);
    }
}
