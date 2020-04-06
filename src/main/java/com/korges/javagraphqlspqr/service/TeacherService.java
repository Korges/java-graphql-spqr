package com.korges.javagraphqlspqr.service;

import com.korges.javagraphqlspqr.entity.Subject;
import com.korges.javagraphqlspqr.entity.Teacher;
import com.korges.javagraphqlspqr.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository repository;

    public List<Teacher> findAll() {
        return repository.findAll();
    }

    public List<Subject> findSubjectListByGivenTeacher(Teacher teacher) {
        return this.repository.findSubjectListByGivenTeacher(teacher.getId());
    }

    public Teacher save(Teacher teacher) {
        return this.repository.save(teacher);
    }
}
