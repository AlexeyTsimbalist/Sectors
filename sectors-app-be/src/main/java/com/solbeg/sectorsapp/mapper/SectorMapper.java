package com.solbeg.sectorsapp.mapper;

import com.solbeg.sectorsapp.dto.SectorResponse;
import com.solbeg.sectorsapp.entity.Sector;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SectorMapper {

    private final ModelMapper modelMapper;

    public SectorResponse modelToResponse(Sector sector) {
        return modelMapper.map(sector, SectorResponse.class);
    }
}