package com.korges.javagraphqlspqr.repository;

import com.korges.javagraphqlspqr.entity.Lecture;
import com.korges.javagraphqlspqr.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Query("SELECT lecture.subject FROM Lecture lecture WHERE lecture.id = :id")
    Subject findSubjectByGivenLecture(@Param("id") Long id);

    List<Lecture> findAllBySubject(@Param("subject") Subject subject);

}
