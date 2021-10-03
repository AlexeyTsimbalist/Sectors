package com.solbeg.task.mapper;

import com.solbeg.task.dto.IndustryResponse;
import com.solbeg.task.entity.Industry;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IndustryMapper {

    private final ModelMapper modelMapper;

    public IndustryResponse modelToResponse(Industry industry) {
        return modelMapper.map(industry, IndustryResponse.class);
    }
}