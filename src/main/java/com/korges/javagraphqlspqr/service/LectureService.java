package com.korges.javagraphqlspqr.service;

import com.korges.javagraphqlspqr.dto.input.LectureInput;
import com.korges.javagraphqlspqr.entity.Lecture;
import com.korges.javagraphqlspqr.entity.Subject;
import com.korges.javagraphqlspqr.repository.LectureRepository;
import com.korges.javagraphqlspqr.repository.SubjectRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.relay.Page;
import io.leangen.graphql.execution.relay.generic.PageFactory;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@GraphQLApi
@Service
@RequiredArgsConstructor
public class LectureService {

    private final ModelMapper modelMapper;
    private final LectureRepository lectureRepository;
    private final SubjectRepository subjectRepository;

    @GraphQLQuery(name = "findAllLecturesByGivenSubject", description = "Retrieves list of all Lectures by given Subject")
    public List<Lecture> findAllLecturesByGivenSubject(@GraphQLNonNull Long id) {
        return subjectRepository.findById(id)
                .map(lectureRepository::findAllBySubject)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Lectures by given Subject id: " + id));
    }

    @GraphQLQuery(name = "findLecture", description = "Retrieve single lecture by given id")
    public Lecture findLectureById(@GraphQLNonNull Long id) {
        return lectureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Subject with id: " + id));
    }

    @GraphQLQuery
    public Page<Lecture> lecturesPaged(@GraphQLArgument(name = "offset", defaultValue = "0") int offset,
                                       @GraphQLNonNull int size) {
        org.springframework.data.domain.Page<Lecture> results = lectureRepository
                .findAll(PageRequest.of(Math.max(offset, 0), Math.max(size, 1), Sort.by("id")));

        return PageFactory.createOffsetBasedPage(
                results.getContent(),
                results.getTotalElements(),
                offset * size);
    }

    @GraphQLQuery(name = "subject", description = "Retrieve Subject by given Lecture")
    public Subject findSubjectByGivenLecture(@GraphQLContext Lecture lecture) {
        return lectureRepository.findSubjectByGivenLecture(lecture.getId());
    }

    @GraphQLMutation(name = "createLecture", description = "Creates a lecture entry")
    public Lecture createLecture(@GraphQLArgument(name = "lecture", description = "The lecture object") @GraphQLNonNull @Valid LectureInput lectureInput) {
        return subjectRepository.findById(lectureInput.getSubject())
                .map(subject -> saveLecture(subject, lectureInput))
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Lecture with id: " + lectureInput.getSubject()));
    }

    private Lecture saveLecture(Subject subject, LectureInput lectureInput) {
        Lecture lecture = modelMapper.map(lectureInput, Lecture.class);
        lecture.setSubject(subject);
        return lectureRepository.save(lecture);
    }

    @GraphQLMutation(name = "deleteLecture", description = "Deletes the given Lecture")
    public String deleteLecture(@GraphQLArgument(name = "id") @GraphQLNonNull Long id) {
        return lectureRepository.findById(id)
                .map(this::deleteLecture)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Lecture by given id: " + id));
    }

    private String deleteLecture(Lecture lecture) {
        lecture.detachLecture(lecture);
        lectureRepository.delete(lecture);

        return "Successfully deleted Lecture with id: " + lecture.getId();
    }
}
