package com.school.schoolManagement.Repository;

import com.school.schoolManagement.Model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    boolean existsBySymbol(String symbol);
}
