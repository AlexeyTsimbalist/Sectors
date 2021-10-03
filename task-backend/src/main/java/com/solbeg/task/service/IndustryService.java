package com.solbeg.task.service;

import com.solbeg.task.dto.IndustryResponse;
import com.solbeg.task.entity.Industry;
import com.solbeg.task.exception.NoSuchResourceException;
import com.solbeg.task.mapper.IndustryMapper;
import com.solbeg.task.repository.IndustryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndustryService {

    private final IndustryRepository industryRepository;
    private final IndustryMapper industryMapper;

    public List<IndustryResponse> findAll() {

        return industryRepository.findAllByParentIsNull()
                .stream()
                .map(industryMapper::modelToResponse)
                .collect(Collectors.toList());
    }

    public Industry findIndustryByUuid(UUID uuid) {
        return industryRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchResourceException("Industry", uuid));
    }
}