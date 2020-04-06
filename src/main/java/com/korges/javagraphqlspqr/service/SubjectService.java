package com.korges.javagraphqlspqr.service;

import com.korges.javagraphqlspqr.pojo.Lecture;
import com.korges.javagraphqlspqr.pojo.Subject;
import com.korges.javagraphqlspqr.pojo.Teacher;
import com.korges.javagraphqlspqr.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository repository;

    public List<Subject> findAll() {
        return repository.findAll();
    }

    public List<Lecture> findLectureListByGivenSubject(Subject subject) {
        return this.repository.findLectureListByGivenSubject(subject.getId());
    }

    public Teacher findTeacherByGivenSubject(Subject subject) {
        return this.repository.findTeacherByGivenSubject(subject.getId());
    }

    public Subject save(Subject subject) {
        return this.repository.save(subject);
    }
}
