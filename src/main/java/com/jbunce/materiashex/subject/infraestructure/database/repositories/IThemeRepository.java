package com.jbunce.materiashex.subject.infraestructure.database.repositories;

import com.jbunce.materiashex.subject.domain.models.Subject;
import com.jbunce.materiashex.subject.infraestructure.database.dao.SubjectEntity;
import com.jbunce.materiashex.subject.infraestructure.database.dao.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IThemeRepository extends JpaRepository<ThemeEntity, String> {

    Optional<ThemeEntity> findByName(String name);

    void deleteAllBySubject(SubjectEntity subject);

}
