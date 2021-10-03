package com.solbeg.task.controller;

import com.solbeg.task.dto.IndustryResponse;
import com.solbeg.task.service.IndustryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Industry", description = "Industry controller")
@CrossOrigin
@RestController
@RequestMapping(path = "/api/industry")
@RequiredArgsConstructor
public class IndustryController {

    private final IndustryService industryService;

    @GetMapping
    @Operation(summary = "Find all industries", description = "Provide method to find all industries")
    public ResponseEntity<List<IndustryResponse>> findAll() {
        List<IndustryResponse> allIndustries = industryService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(allIndustries);
    }
}