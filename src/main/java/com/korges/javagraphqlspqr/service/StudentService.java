package com.korges.javagraphqlspqr.service;

import com.korges.javagraphqlspqr.dto.input.PersonInput;
import com.korges.javagraphqlspqr.entity.Student;
import com.korges.javagraphqlspqr.entity.Subject;
import com.korges.javagraphqlspqr.repository.StudentRepository;
import com.korges.javagraphqlspqr.repository.SubjectRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@GraphQLApi
@Service
@RequiredArgsConstructor
public class StudentService {

    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

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

    @GraphQLMutation(name = "assignSubjectToStudent", description = "Assign new Subject to Student")
    public Student assignSubject(@GraphQLArgument(name = "studentId") @GraphQLNonNull Long studentId,
                                 @GraphQLArgument(name = "subjectId") @GraphQLNonNull Long subjectId) {
        return subjectRepository.findById(subjectId)
                .map(subject -> assignSubject(subject, studentId))
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Subject by given id: " + subjectId));
    }

    public Student assignSubject(Subject subject, Long studentId) {
        return studentRepository.findById(studentId)
                .map(student -> {
                    student.getSubjectList().add(subject);
                    return studentRepository.save(student);
                }).orElseThrow(() -> new EntityNotFoundException("Unable to find Student by given id: " + studentId));
    }

    @GraphQLMutation(name = "addStudent", description = "Add new Student")
    public Student addStudent(@GraphQLArgument(name = "student", description = "The Student object") @GraphQLNonNull @Valid PersonInput person) {
        return this.studentRepository.save(modelMapper.map(person, Student.class));
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
