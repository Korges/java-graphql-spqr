package com.korges.javagraphqlspqr.service;

import com.korges.javagraphqlspqr.entity.Subject;
import com.korges.javagraphqlspqr.repository.LectureRepository;
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
import javax.transaction.Transactional;
import java.util.List;

@GraphQLApi
@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;

    @GraphQLQuery(name = "findAllSubjects", description = "Retrieves list of all Subjects")
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    @GraphQLQuery(name = "findSubject", description = "Retrieve single Subject by given id")
    public Subject findLectureById(@GraphQLArgument(name = "id") @GraphQLNonNull Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Subject with id: " + id));
    }

//    public List<Lecture> findLectureListByGivenSubject(Subject subject) {
//        return this.subjectRepository.findLectureListByGivenSubject(subject.getId());
//    }

//    public Teacher findTeacherByGivenSubject(Subject subject) {
//        return this.subjectRepository.findTeacherByGivenSubject(subject.getId());
//    }

    @GraphQLMutation(name = "createSubject", description = "Creates a Subject entry")
    public Subject createSubject(@GraphQLArgument(name = "subjectTitle") @GraphQLNonNull String subjectTitle) {
        Subject subject = new Subject();
        subject.setTitle(subjectTitle);
        return this.subjectRepository.save(subject);
    }

    @Transactional
    @GraphQLMutation(name = "deleteSubject", description = "Deleted the given Subject")
    public String deleteSubject(@GraphQLArgument(name = "id") @GraphQLNonNull Long id) {
        return subjectRepository.findById(id)
                .map(this::deleteSubject)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Subject by given id: " + id));
    }

    private String deleteSubject(Subject subject) {
        subject.detachSubject(subject);
        studentRepository.findStudentsBySubjectListContaining(subject)
                .forEach(student -> student.detachSubject(subject));
        subjectRepository.delete(subject);

        return "Successfully deleted Subject with id: " + subject.getId();
    }
}
