package com.school.schoolManagement.Repository;

import com.school.schoolManagement.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByPesel(String pesel);
}
