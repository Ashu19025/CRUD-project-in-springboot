package com.example.personalblogging.Repository;

import com.example.personalblogging.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Student,Integer> {

}
