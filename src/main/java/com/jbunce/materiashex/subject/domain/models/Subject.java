package com.jbunce.materiashex.subject.domain.models;

import com.jbunce.materiashex.subject.domain.models.value.objects.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class Subject {

    @NotBlank
    private String uuid;

    @NotBlank
    private String name;

    @NotBlank
    private String career;

    @NotNull
    private Status status;

    @NotEmpty
    private Set<Theme> themes;

}
