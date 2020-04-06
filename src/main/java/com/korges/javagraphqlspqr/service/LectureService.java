package com.korges.javagraphqlspqr.service;

import com.korges.javagraphqlspqr.entity.Lecture;
import com.korges.javagraphqlspqr.entity.Subject;
import com.korges.javagraphqlspqr.repository.LectureRepository;
import com.korges.javagraphqlspqr.repository.SubjectRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.ResolutionEnvironment;
import io.leangen.graphql.execution.relay.Page;
import io.leangen.graphql.execution.relay.generic.PageFactory;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@GraphQLApi
@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository repository;
    private final SubjectRepository subjectRepository;

    @GraphQLQuery(name = "lectures", description = "aaaaaa")
    public List<Lecture> findAllLectures() {
        return repository.findAll();
    }

    @GraphQLQuery(name = "lecture")
    public Optional<Lecture> findLectureById(@GraphQLEnvironment ResolutionEnvironment environment, Long id) {
        return repository.findById(id);
    }

    @GraphQLQuery
    public Page<Lecture> lecturesPaged(@GraphQLArgument(name = "offset", defaultValue = "0") int offset,
                                       @GraphQLNonNull int size) {
        org.springframework.data.domain.Page<Lecture> results = repository
                .findAll(PageRequest.of(Math.max(offset, 0), Math.max(size, 1), Sort.by("id")));

        return PageFactory.createOffsetBasedPage(
                results.getContent(),
                results.getTotalElements(),
                offset * size);
    }

    @GraphQLQuery(name = "subject", description = "dddddd")
    public Optional<Subject> getSubjectByGivenLecture(@GraphQLContext Lecture lecture) {
        return repository.findSubjectByGivenLecture(lecture.getId());
    }

    @GraphQLMutation(name = "createLecture", description = "Creates a lecture entry")
    public Lecture createLecture(@GraphQLArgument(name = "lecture", description = "The lecture object") @GraphQLNonNull @Valid Lecture lecture) {
        return repository.save(lecture);
    }
}
