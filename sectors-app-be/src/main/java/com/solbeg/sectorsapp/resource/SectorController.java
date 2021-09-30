package com.solbeg.sectorsapp.resource;

import com.solbeg.sectorsapp.dto.SectorResponse;
import com.solbeg.sectorsapp.entity.Sector;
import com.solbeg.sectorsapp.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/sector")
@RequiredArgsConstructor
public class SectorController {

    private final SectorService sectorService;

    @GetMapping
    public ResponseEntity<List<SectorResponse>> findAll() {

        List<SectorResponse> allSectors = sectorService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(allSectors);
    }
}