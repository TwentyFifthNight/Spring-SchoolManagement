package com.school.schoolManagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String symbol;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Student> studentList;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Course> courseList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher supervisor;
}
