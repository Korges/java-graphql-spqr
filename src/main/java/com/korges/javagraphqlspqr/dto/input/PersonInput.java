package com.korges.javagraphqlspqr.dto.input;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Validated
public class PersonInput {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}