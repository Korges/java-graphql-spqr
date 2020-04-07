package com.korges.javagraphqlspqr.service;

import com.korges.javagraphqlspqr.entity.Student;
import com.korges.javagraphqlspqr.entity.Subject;
import com.korges.javagraphqlspqr.repository.StudentRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@GraphQLApi
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @GraphQLQuery(name = "students")
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @GraphQLQuery(name = "findStudent", description = "Retrieve Student by given id")
    public Student findStudent(@GraphQLArgument(name ="id") @GraphQLNonNull Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Student by given id: " + id));
    }

    @GraphQLQuery(name = "subject")
    public List<Subject> findSubjectListByGivenStudent(Student student) {
        return studentRepository.findSubjectListByStudent(student.getId());
    }

    @GraphQLMutation
    public Student save(Student student) {
        return this.studentRepository.save(student);
    }
}
