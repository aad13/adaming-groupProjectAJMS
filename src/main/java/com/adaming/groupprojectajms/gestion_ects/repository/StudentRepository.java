package com.adaminggroupProjectAJMS.poc.repository;

import com.adaminggroupProjectAJMS.poc.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {

    @Query("SELECT s FROM Student As s WHERE s.email=:email")
    Student getStudentByEmail(String email);
}
