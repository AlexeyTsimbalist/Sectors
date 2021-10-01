package com.solbeg.sectorsapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbeg.sectorsapp.dto.FormRequest;
import com.solbeg.sectorsapp.dto.FormResponse;
import com.solbeg.sectorsapp.dto.SectorResponse;
import com.solbeg.sectorsapp.entity.Form;
import com.solbeg.sectorsapp.entity.Sector;

import java.util.Random;

public class TestUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertObjectToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static Sector buildSector() {
        return Sector.builder()
                .id(new Random().nextLong())
                .name("Sector name")
                .build();
    }

    public static SectorResponse buildSectorResponse() {
        return SectorResponse.builder()
                .id(new Random().nextLong())
                .name("Sector name")
                .build();
    }

    public static FormRequest buildFormRequest() {
        return FormRequest.builder()
                .username("User")
                .sector(2L)
                .agreement(true)
                .build();
    }

    public static Form buildForm() {
        return Form.builder()
                .id(new Random().nextLong())
                .username("User")
                .sector(buildSector())
                .agreement(true)
                .build();

    }

    public static FormResponse buildFormResponse() {
        return FormResponse.builder()
                .id(new Random().nextLong())
                .username("User")
                .sectorId(2L)
                .agreement(true)
                .build();
    }
}