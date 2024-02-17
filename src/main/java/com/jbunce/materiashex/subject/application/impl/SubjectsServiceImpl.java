package com.jbunce.materiashex.subject.application.impl;

import com.jbunce.materiashex.configurations.BaseResponse;
import com.jbunce.materiashex.subject.application.ISubjectService;
import com.jbunce.materiashex.subject.application.IThemeService;
import com.jbunce.materiashex.subject.domain.models.Subject;
import com.jbunce.materiashex.subject.infraestructure.database.dao.SubjectEntity;
import com.jbunce.materiashex.subject.infraestructure.database.dao.ThemeEntity;
import com.jbunce.materiashex.subject.infraestructure.database.repositories.ISubjectRepository;
import com.jbunce.materiashex.subject.infraestructure.mapper.ISubjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectsServiceImpl implements ISubjectService {

    @Autowired
    private ISubjectRepository repository;

    @Autowired
    private ISubjectMapper mapper;

    @Autowired
    private IThemeService themeService;

    @Override
    public BaseResponse getAllSubjects() {
        List<Subject> subjects = repository.findAll().stream()
                .map(mapper::toDomain).toList();

        return BaseResponse.builder()
                .data(subjects)
                .message("The subjects were found")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse getSubjectByName(String name) {
        SubjectEntity subjectEntity = repository.findByName(name)
                .orElseThrow(EntityNotFoundException::new);

        Subject subject = mapper.toDomain(subjectEntity);

        return BaseResponse.builder()
                .data(subject)
                .message("The subject with name: " + name + " was found")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse getSubjectByUUID(String uuid) {
        SubjectEntity subjectEntity = repository.findById(uuid)
                .orElseThrow(EntityNotFoundException::new);

        Subject subject = mapper.toDomain(subjectEntity);

        return BaseResponse.builder()
                .data(subject)
                .message("The subject with id: " + uuid + " was found")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse getSubjectByThemeUUID(String themeUUID) {
        ThemeEntity theme = themeService.findOneAndEnsureExists(themeUUID);

        SubjectEntity subjectEntity = repository.findByThemesContains(theme)
                .orElseThrow(EntityNotFoundException::new);

        Subject subject = mapper.toDomain(subjectEntity);

        return BaseResponse.builder()
                .data(subject)
                .message("The subject with theme id: " + themeUUID + " was found")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse createSubject(Subject subjectRequest) {
        SubjectEntity savedSubject = repository.save(mapper.toEntity(subjectRequest));

        themeService.saveSubjectThemes(subjectRequest.getThemes(), savedSubject);
        Subject subject = mapper.toDomain(savedSubject);

        return BaseResponse.builder()
                .data(subject)
                .message("The subject was saved with id: " + savedSubject.getUuid() + " successfully")
                .success(Boolean.TRUE)
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value()).build();
    }

    @Override
    public BaseResponse updateSubject(Subject subject, String subjectUUID) {
        SubjectEntity subjectEntity = repository.findById(subjectUUID)
                .orElseThrow(EntityNotFoundException::new);

        updateSubject(subject, subjectEntity);
        themeService.updateSubjects(subject.getThemes(), subjectEntity);
        Subject updatedSubject = mapper.toDomain(repository.save(subjectEntity));

        return BaseResponse.builder()
                .data(updatedSubject)
                .message("The subject with id: " + subjectUUID + " was updated successfully")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse deleteSubject(String subjectUUID) {
        SubjectEntity subject = repository.findById(subjectUUID).orElseThrow(EntityNotFoundException::new);
        repository.delete(subject);

        return BaseResponse.builder()
                .message("The subject with id: " + subjectUUID + " was deleted successfully")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value()).build();
    }

    private void updateSubject(Subject subject, SubjectEntity subjectEntity) {
        subjectEntity.setName(subject.getName());
        subjectEntity.setCareer(subject.getCareer());
        subjectEntity.setStatus(subject.getStatus());
    }
}
