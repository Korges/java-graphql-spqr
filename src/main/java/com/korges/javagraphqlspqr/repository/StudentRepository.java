package com.korges.javagraphqlspqr.repository;

import com.korges.javagraphqlspqr.pojo.Student;
import com.korges.javagraphqlspqr.pojo.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT student.subjectList FROM Student student WHERE student.id = :id")
    List<Subject> findSubjectListByStudent(@Param("id") Long id);

}
