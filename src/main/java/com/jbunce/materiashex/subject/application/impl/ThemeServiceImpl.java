package com.jbunce.materiashex.subject.application.impl;

import com.jbunce.materiashex.configurations.BaseResponse;
import com.jbunce.materiashex.subject.application.IThemeService;
import com.jbunce.materiashex.subject.domain.models.Subject;
import com.jbunce.materiashex.subject.domain.models.Theme;
import com.jbunce.materiashex.subject.infraestructure.database.dao.SubjectEntity;
import com.jbunce.materiashex.subject.infraestructure.database.dao.ThemeEntity;
import com.jbunce.materiashex.subject.infraestructure.database.repositories.IThemeRepository;
import com.jbunce.materiashex.subject.infraestructure.mapper.IThemeMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ThemeServiceImpl implements IThemeService {

    @Autowired
    private IThemeRepository repository;

    @Autowired
    private IThemeMapper mapper;

    @Override
    public BaseResponse getAllThemes() {
        List<Theme> themes = repository.findAll().stream()
                .map(mapper::toDomain).toList();

        return BaseResponse.builder()
                .data(themes)
                .message("The themes were found")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse getThemeByName(String name) {
        ThemeEntity themeEntity = repository.findByName(name)
                .orElseThrow(EntityNotFoundException::new);

        Theme theme = mapper.toDomain(themeEntity);

        return BaseResponse.builder()
                .data(theme)
                .message("The theme with name: " + name + " was found")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse getThemeByUUID(String uuid) {
        ThemeEntity themeEntity = repository.findById(uuid)
                .orElseThrow(EntityNotFoundException::new);

        Theme theme = mapper.toDomain(themeEntity);

        return BaseResponse.builder()
                .data(theme)
                .message("The theme with uuid: " + uuid + " was found")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse updateTheme(Theme theme, String themeUUID) {
        ThemeEntity themeEntity = repository.findById(themeUUID)
                .orElseThrow(EntityNotFoundException::new);

        updateThemeEntity(theme, themeEntity);
        repository.save(themeEntity);
        Theme updatedTheme = mapper.toDomain(themeEntity);

        return BaseResponse.builder()
                .data(updatedTheme)
                .message("The theme with uuid: " + themeUUID + " was updated")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse deleteTheme(String themeUUID) {
        ThemeEntity themeEntity = repository.findById(themeUUID)
                .orElseThrow(EntityNotFoundException::new);

        repository.delete(themeEntity);

        return BaseResponse.builder()
                .data(null)
                .message("The theme with uuid: " + themeUUID + " was deleted")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value()).build();
    }

    @Override
    public void saveSubjectThemes(Set<Theme> themes, SubjectEntity subjectEntity) {
        saveThemes(themes, subjectEntity);
    }

    @Override
    public void updateSubjects(Set<Theme> themes, SubjectEntity subjectEntity) {
        repository.deleteAllBySubject(subjectEntity);
        saveThemes(themes, subjectEntity);
    }

    @Override
    public ThemeEntity findOneAndEnsureExists(String name) {
        return repository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }

    private void saveThemes(Set<Theme> themes, SubjectEntity subjectEntity) {
        List<ThemeEntity> themeEntities = themes.stream()
                .map(theme -> this.toThemeEntity(theme, subjectEntity)).toList();

        repository.saveAll(themeEntities);
    }

    private ThemeEntity toThemeEntity(Theme theme, SubjectEntity subjectEntity) {
        ThemeEntity themeEntity = mapper.toEntity(theme);
        themeEntity.setSubject(subjectEntity);
        return themeEntity;
    }

    private void updateThemeEntity(Theme theme, ThemeEntity themeEntity) {
        themeEntity.setName(theme.getName());
        themeEntity.setStatus(theme.getStatus());
    }
}
