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
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private String phone;

    @OneToOne(mappedBy = "supervisor")
    private Classroom supervisedClass;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Course> courseList;
}
