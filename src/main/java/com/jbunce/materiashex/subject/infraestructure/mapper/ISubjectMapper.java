package com.jbunce.materiashex.subject.infraestructure.mapper;

import com.jbunce.materiashex.subject.domain.models.Subject;
import com.jbunce.materiashex.subject.infraestructure.database.dao.SubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ISubjectMapper {

    Subject toDomain(SubjectEntity subjectEntity);

    @Mapping(target = "themes", ignore = true)
    SubjectEntity toEntity(Subject subject);

}
