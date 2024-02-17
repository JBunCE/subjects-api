package com.jbunce.materiashex.subject.domain.models;

import com.jbunce.materiashex.subject.domain.models.value.objects.Status;
import com.jbunce.materiashex.subject.infraestructure.database.dao.SubjectEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Theme {

    @NotBlank
    private String uuid;

    @NotBlank
    private String name;

    @NotNull
    private Status status;

    @NotNull
    private SubjectEntity subject;

}
