package com.solbeg.sectorsapp.controller;

import com.solbeg.sectorsapp.dto.SectorResponse;
import com.solbeg.sectorsapp.service.SectorService;
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

@Tag(name = "Sectors", description = "Sectors controller")
@CrossOrigin
@RestController
@RequestMapping(path = "/api/sector")
@RequiredArgsConstructor
public class SectorController {

    private final SectorService sectorService;

    @GetMapping
    @Operation(summary = "Find all sectors", description = "Provide method to find all sectors")
    public ResponseEntity<List<SectorResponse>> findAll() {
        List<SectorResponse> allSectors = sectorService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(allSectors);
    }
}