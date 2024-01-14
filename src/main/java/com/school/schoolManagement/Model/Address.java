package com.school.schoolManagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String houseNumber;
    private String street;
    private String city;
    private String zipCode;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Student> studentList;

}
