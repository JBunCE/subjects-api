package com.jbunce.materiashex.subject.infraestructure.database.dao;

import com.jbunce.materiashex.subject.domain.models.value.objects.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "themes")
@Getter @Setter
public class ThemeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String name;

    private Status status;

    @ManyToOne
    private SubjectEntity subject;

}
