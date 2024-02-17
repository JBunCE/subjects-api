package com.jbunce.materiashex.subject.infraestructure.database.repositories;

import com.jbunce.materiashex.subject.infraestructure.database.dao.SubjectEntity;
import com.jbunce.materiashex.subject.infraestructure.database.dao.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISubjectRepository extends JpaRepository<SubjectEntity, String> {

    Optional<SubjectEntity> findByName(String name);

    Optional<SubjectEntity> findByThemesContains(ThemeEntity themeEntity);

}
