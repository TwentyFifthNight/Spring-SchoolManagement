package com.school.schoolManagement.Repository;

import com.school.schoolManagement.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    boolean existsByHouseNumberAndStreetAndCityAndZipCode(String houseNumber, String street,
                                                          String city, String zipCode);
}
