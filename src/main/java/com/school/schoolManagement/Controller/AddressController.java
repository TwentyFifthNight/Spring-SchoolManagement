package com.school.schoolManagement.Controller;

import com.school.schoolManagement.Dto.AddressDto;
import com.school.schoolManagement.Dto.Request.Address.CreateAddressRequest;
import com.school.schoolManagement.Dto.Request.Address.UpdateAddressRequest;
import com.school.schoolManagement.Service.AddressService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Void> createAddress(@Valid CreateAddressRequest request) {
        addressService.createAddress(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable Long id,
                                              @Valid UpdateAddressRequest request) {
        addressService.updateAddress(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> findAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.findAddressById(id));
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> findAllAddresses() {
        return ResponseEntity.ok(addressService.findAllAddresses());
    }
}
