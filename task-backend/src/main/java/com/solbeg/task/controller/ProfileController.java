package com.solbeg.task.controller;

import com.solbeg.task.dto.ProfileRequest;
import com.solbeg.task.dto.ProfileResponse;
import com.solbeg.task.service.ProfileService;
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

@Tag(name = "Profile", description = "Profile controller")
@CrossOrigin
@RestController
@RequestMapping(path = "/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    @Operation(summary = "Save profile", description = "Provide method to save new profile")
    public ResponseEntity<ProfileResponse> save(@Valid @RequestBody ProfileRequest profileRequest) {
        ProfileResponse savedProfile = profileService.save(profileRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    @PutMapping(path = "/{uuid}")
    @Operation(summary = "Update profile", description = "Provide method to update existing profile by UUID")
    public ResponseEntity<ProfileResponse> update(@PathVariable String uuid, @Valid @RequestBody ProfileRequest profileRequest) {
        ProfileResponse updatedProfile = profileService.update(uuid, profileRequest);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProfile);
    }
}