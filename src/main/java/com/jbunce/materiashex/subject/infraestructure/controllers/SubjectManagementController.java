package com.jbunce.materiashex.subject.infraestructure.controllers;

import com.jbunce.materiashex.configurations.BaseResponse;
import com.jbunce.materiashex.subject.application.ISubjectService;
import com.jbunce.materiashex.subject.domain.models.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("subject")
public class SubjectManagementController {

    @Autowired
    private ISubjectService service;

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> findAll() {
        return service.getAllSubjects().apply();
    }

    @GetMapping("/{subjectName}")
    public ResponseEntity<BaseResponse> findByName(@PathVariable String subjectName) {
        return service.getSubjectByName(subjectName).apply();
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<BaseResponse> findByUUID(@PathVariable String uuid) {
        return service.getSubjectByUUID(uuid).apply();
    }

    @GetMapping("/theme/{themeUUID}")
    public ResponseEntity<BaseResponse> findByThemeUUID(@PathVariable String themeUUID) {
        return service.getSubjectByThemeUUID(themeUUID).apply();
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> create(@RequestBody Subject subject) {
        return service.createSubject(subject).apply();
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<BaseResponse> update(@RequestBody Subject subject, @PathVariable String uuid) {
        return service.updateSubject(subject, uuid).apply();
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<BaseResponse> delete(@PathVariable String uuid) {
        return service.deleteSubject(uuid).apply();
    }

}
