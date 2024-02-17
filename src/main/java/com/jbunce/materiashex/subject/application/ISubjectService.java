package com.jbunce.materiashex.subject.application;

import com.jbunce.materiashex.configurations.BaseResponse;
import com.jbunce.materiashex.subject.domain.models.Subject;

public interface ISubjectService {

    BaseResponse getAllSubjects();

    BaseResponse getSubjectByName(String name);

    BaseResponse getSubjectByUUID(String uuid);

    BaseResponse getSubjectByThemeUUID(String themeUUID);

    BaseResponse createSubject(Subject subjectRequest);

    BaseResponse updateSubject(Subject subject, String subjectUUID);

    BaseResponse deleteSubject(String subjectUUID);

}
