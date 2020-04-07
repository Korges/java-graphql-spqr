package com.korges.javagraphqlspqr.service;

import com.korges.javagraphqlspqr.entity.Subject;
import com.korges.javagraphqlspqr.repository.StudentRepository;
import com.korges.javagraphqlspqr.repository.SubjectRepository;
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
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    @GraphQLQuery(name = "findAllSubjects", description = "Retrieves list of all Subjects")
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

//    public List<Lecture> findLectureListByGivenSubject(Subject subject) {
//        return this.subjectRepository.findLectureListByGivenSubject(subject.getId());
//    }

//    public Teacher findTeacherByGivenSubject(Subject subject) {
//        return this.subjectRepository.findTeacherByGivenSubject(subject.getId());
//    }

    public Subject save(Subject subject) {
        return this.subjectRepository.save(subject);
    }

    @GraphQLMutation(name = "deleteSubject", description = "Deleted the given Subject")
    public String deleteSubject(@GraphQLArgument(name = "id") @GraphQLNonNull Long id) {
        return subjectRepository.findById(id)
                .map(this::deleteSubject)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Subject by given id: " + id));
    }

    private String deleteSubject(Subject subject) {
//        studentRepository.findStudentsBySubjectListContaining(subject)
//                .forEach(x -> {
//                    x.getSubjectList().remove(subject);
//                    studentRepository.save(x);
//                });
        studentRepository.findAll().forEach(
                x -> System.out.println("SIZE " + x.getSubjectList().size())
        );
        subjectRepository.delete(subject);
        return "Successfully deleted Student with id: " + subject.getId();
    }
}
