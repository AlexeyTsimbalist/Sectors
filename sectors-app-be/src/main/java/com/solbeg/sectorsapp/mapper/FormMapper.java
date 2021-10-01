package com.solbeg.sectorsapp.mapper;

import com.solbeg.sectorsapp.dto.FormRequest;
import com.solbeg.sectorsapp.dto.FormResponse;
import com.solbeg.sectorsapp.entity.Form;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FormMapper {

    private final ModelMapper modelMapper;

    public Form requestToModel(FormRequest formRequest) {
        return modelMapper.map(formRequest, Form.class);
    }

    public void requestToModel(FormRequest formRequest, Form form) {
        modelMapper.map(formRequest, form);
    }

    public FormResponse modelToResponse(Form form) {
        return modelMapper.map(form, FormResponse.class);
    }
}