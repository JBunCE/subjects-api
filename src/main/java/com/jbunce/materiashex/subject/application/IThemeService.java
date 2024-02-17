package com.jbunce.materiashex.subject.application;

import com.jbunce.materiashex.configurations.BaseResponse;
import com.jbunce.materiashex.subject.domain.models.Subject;
import com.jbunce.materiashex.subject.domain.models.Theme;
import com.jbunce.materiashex.subject.infraestructure.database.dao.SubjectEntity;
import com.jbunce.materiashex.subject.infraestructure.database.dao.ThemeEntity;

import java.util.List;
import java.util.Set;

public interface IThemeService {

    BaseResponse getAllThemes();

    BaseResponse getThemeByName(String name);

    BaseResponse getThemeByUUID(String uuid);

    BaseResponse updateTheme(Theme theme, String themeUUID);

    BaseResponse deleteTheme(String themeUUID);

    void saveSubjectThemes(Set<Theme> themes, SubjectEntity subjectEntity);

    void updateSubjects(Set<Theme> themes, SubjectEntity subjectEntity);

    ThemeEntity findOneAndEnsureExists(String name);

}
