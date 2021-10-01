package com.solbeg.sectorsapp.service;

import com.solbeg.sectorsapp.dto.SectorResponse;
import com.solbeg.sectorsapp.entity.Sector;
import com.solbeg.sectorsapp.exception.NoSuchResourceException;
import com.solbeg.sectorsapp.mapper.SectorMapper;
import com.solbeg.sectorsapp.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;

    public List<SectorResponse> findAll() {

        return sectorRepository.findAllByParentIsNull()
                .stream()
                .map(sectorMapper::modelToResponse)
                .collect(Collectors.toList());
    }

    public Sector findSectorById(Long id) {
        return sectorRepository.findById(id)
                .orElseThrow(() -> new NoSuchResourceException("Sector", id));
    }
}