package com.korges.javagraphqlspqr.dto.input;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Validated
public class LectureInput {
    @NotBlank
    private String description;
    @NotNull
    private Long subject;
}