package com.korges.javagraphqlspqr.repository;

import com.korges.javagraphqlspqr.pojo.Lecture;
import com.korges.javagraphqlspqr.pojo.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    Optional<Lecture> findLectureById(@Param("id") Long id);

    @Query("SELECT lecture.subject FROM Lecture lecture WHERE lecture.id = :id")
    Optional<Subject> findSubjectByGivenLecture(@Param("id") Long id);





}
