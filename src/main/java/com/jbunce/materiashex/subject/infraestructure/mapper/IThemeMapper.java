package com.jbunce.materiashex.subject.infraestructure.mapper;

import com.jbunce.materiashex.subject.domain.models.Theme;
import com.jbunce.materiashex.subject.infraestructure.database.dao.ThemeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface IThemeMapper {

    Theme toDomain(ThemeEntity themeEntity);

    @Mapping(target = "subject", ignore = true)
    ThemeEntity toEntity(Theme theme);

}
