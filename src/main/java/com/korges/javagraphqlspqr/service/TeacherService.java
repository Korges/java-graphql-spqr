package com.korges.javagraphqlspqr.service;

import com.korges.javagraphqlspqr.entity.Teacher;
import com.korges.javagraphqlspqr.repository.SubjectRepository;
import com.korges.javagraphqlspqr.repository.TeacherRepository;
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
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    @GraphQLQuery(name = "findAllTeachers", description = "Retrieves list of all Teachers")
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @GraphQLQuery(name = "findTeacher", description = "Retrieve Teacher by given id")
    public Teacher findTeacher(@GraphQLArgument(name ="id") @GraphQLNonNull Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Teacher by given id: " + id));
    }

//    @GraphQLQuery
//    public List<Subject> findSubjectListByGivenTeacher(@GraphQLContext Teacher teacher) {
//        return this.teacherRepository.findSubjectListByGivenTeacher(teacher.getId());
//    }

    @GraphQLMutation(name = "addTeacher", description = "Add new Teacher")
    public Teacher addTeacher(@GraphQLArgument(name = "lecture", description = "The teacher object") Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    @GraphQLMutation(name = "deleteTeacher", description = "Deletes the given Teacher")
    public String deleteTeacher(@GraphQLArgument(name = "id") @GraphQLNonNull Long id) {
        return teacherRepository.findById(id)
                .map(this::deleteTeacher)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Teacher by given id: " + id));
    }

    private String deleteTeacher(Teacher teacher) {
        subjectRepository.findAllByTeacher(teacher)
                .forEach(subject -> subject.setTeacher(null));

        teacherRepository.delete(teacher);
        return "Successfully deleted Student with id: " + teacher.getId();
    }
}
