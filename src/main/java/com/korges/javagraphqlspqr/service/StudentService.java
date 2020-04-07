package com.korges.javagraphqlspqr.service;

import com.korges.javagraphqlspqr.entity.Student;
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

@GraphQLApi
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @GraphQLQuery(name = "findAllStudents", description = "Retrieves list of all Teachers")
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @GraphQLQuery(name = "findStudent", description = "Retrieve Student by given id")
    public Student findStudent(@GraphQLArgument(name ="id") @GraphQLNonNull Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Student by given id: " + id));
    }

//    @GraphQLQuery(name = "subjectList")
//    public List<Subject> findSubjectListByGivenStudent(@GraphQLContext Student student) {
//        return studentRepository.findSubjectListByStudent(student.getId());
//    }

    @GraphQLMutation(name = "addStudent", description = "Add new Student")
    public Student addStudent(Student student) {
        return this.studentRepository.save(student);
    }

    @GraphQLMutation(name = "deleteStudent", description = "Deletes the given Student")
    public String deleteStudent(@GraphQLArgument(name = "id") @GraphQLNonNull Long id) {
        return studentRepository.findById(id)
                .map(this::deleteStudent)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Student by given id: " + id));
    }

    private String deleteStudent(Student student) {
        studentRepository.delete(student);
        return "Successfully deleted Student with id: " + student.getId();
    }

}
