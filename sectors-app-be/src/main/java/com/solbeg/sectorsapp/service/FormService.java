package com.solbeg.sectorsapp.service;

import com.solbeg.sectorsapp.dto.FormRequest;
import com.solbeg.sectorsapp.dto.FormResponse;
import com.solbeg.sectorsapp.entity.Form;
import com.solbeg.sectorsapp.entity.Sector;
import com.solbeg.sectorsapp.exception.NoSuchResourceException;
import com.solbeg.sectorsapp.mapper.FormMapper;
import com.solbeg.sectorsapp.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FormService {

    private final FormRepository formRepository;
    private final SectorService sectorService;
    private final FormMapper formMapper;

    public FormResponse save(FormRequest formRequest) {
        Sector sector = sectorService.findSectorById(formRequest.getSector());

        Form form = formMapper.requestToModel(formRequest);
        form.setSector(sector);

        Form savedForm = formRepository.save(form);

        return formMapper.modelToResponse(savedForm);
    }

    public FormResponse update(Long formId, FormRequest formRequest) {
        Form existingForm = findFormById(formId);
        Sector sector = sectorService.findSectorById(formRequest.getSector());

        formMapper.requestToModel(formRequest, existingForm);
        existingForm.setSector(sector);

        existingForm = formRepository.save(existingForm);

        return formMapper.modelToResponse(existingForm);
    }

    private Form findFormById(Long id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new NoSuchResourceException("Form", id));
    }
}