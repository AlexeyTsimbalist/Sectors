package com.solbeg.sectorsapp.controller;

import com.solbeg.sectorsapp.dto.FormRequest;
import com.solbeg.sectorsapp.dto.FormResponse;
import com.solbeg.sectorsapp.service.FormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Forms", description = "Forms controller")
@CrossOrigin
@RestController
@RequestMapping(path = "/api/form")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;

    @PostMapping
    @Operation(summary = "Save form", description = "Provide method to save new form")
    public ResponseEntity<FormResponse> save(@Valid @RequestBody FormRequest formRequest) {
        FormResponse savedForm = formService.save(formRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedForm);
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Update form", description = "Provide method to update existing form by ID")
    public ResponseEntity<FormResponse> update(@PathVariable Long id, @Valid @RequestBody FormRequest formRequest) {
        FormResponse updatedForm = formService.update(id, formRequest);

        return ResponseEntity.status(HttpStatus.OK).body(updatedForm);
    }
}