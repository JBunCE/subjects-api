package com.jbunce.materiashex.subject.infraestructure.database.dao;


import com.jbunce.materiashex.subject.domain.models.value.objects.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "subjects")
@Getter @Setter
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String name;

    private String career;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "subject")
    private Set<ThemeEntity> themes;

}
