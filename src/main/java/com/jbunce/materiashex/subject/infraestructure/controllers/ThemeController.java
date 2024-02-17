package com.jbunce.materiashex.subject.infraestructure.controllers;

import com.jbunce.materiashex.configurations.BaseResponse;
import com.jbunce.materiashex.subject.application.IThemeService;
import com.jbunce.materiashex.subject.domain.models.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("theme")
public class ThemeController {

    @Autowired
    private IThemeService service;

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> findAll() {
        return service.getAllThemes().apply();
    }

    @GetMapping("/name/{themeName}")
    public ResponseEntity<BaseResponse> findByName(@PathVariable String themeName) {
        return service.getThemeByName(themeName).apply();
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<BaseResponse> findByUUID(@PathVariable String uuid) {
        return service.getThemeByUUID(uuid).apply();
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<BaseResponse> update(@RequestBody Theme theme, @PathVariable String uuid) {
        return service.updateTheme(theme, uuid).apply();
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<BaseResponse> delete(@PathVariable String uuid) {
        return service.deleteTheme(uuid).apply();
    }

}
