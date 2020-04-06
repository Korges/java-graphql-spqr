package com.korges.javagraphqlspqr.repository;

import com.korges.javagraphqlspqr.pojo.Lecture;
import com.korges.javagraphqlspqr.pojo.Subject;
import com.korges.javagraphqlspqr.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT subject.lectureList FROM Subject subject WHERE subject.id = :id")
    List<Lecture> findLectureListByGivenSubject(@Param("id") Long id);

    @Query("SELECT subject.teacher FROM Subject subject WHERE subject.id = :id")
    Teacher findTeacherByGivenSubject(@Param("id") Long id);

}
