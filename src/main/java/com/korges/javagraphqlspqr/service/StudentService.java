package com.korges.javagraphqlspqr.service;

import com.korges.javagraphqlspqr.entity.Student;
import com.korges.javagraphqlspqr.entity.Subject;
import com.korges.javagraphqlspqr.repository.StudentRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@GraphQLApi
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    @GraphQLQuery(name = "students")
    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    @GraphQLQuery(name = "student")
    public Optional<Student> findStudent(@GraphQLArgument(name ="id") Long id) {
        return repository.findById(id);
    }

    @GraphQLQuery(name = "subject")
    public List<Subject> findSubjectListByGivenStudent(Student student) {
        return repository.findSubjectListByStudent(student.getId());
    }

    @GraphQLMutation
    public Student save(Student student) {
        return this.repository.save(student);
    }
}
