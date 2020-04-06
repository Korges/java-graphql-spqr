package com.korges.javagraphqlspqr.repository;

import com.korges.javagraphqlspqr.pojo.Subject;
import com.korges.javagraphqlspqr.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT teacher.subjectList FROM Teacher teacher WHERE teacher.id = :id")
    List<Subject> findSubjectListByGivenTeacher(@Param("id") Long id);

}
