package com.solbeg.task.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbeg.task.dto.ProfileRequest;
import com.solbeg.task.dto.ProfileResponse;
import com.solbeg.task.dto.IndustryResponse;
import com.solbeg.task.entity.Profile;
import com.solbeg.task.entity.Industry;

import java.util.UUID;

public class TestUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertObjectToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static Industry buildIndustry() {
        return Industry.builder()
                .uuid(UUID.randomUUID())
                .name("Industry name")
                .build();
    }

    public static IndustryResponse buildIndustryResponse() {
        return IndustryResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .name("Industry name")
                .build();
    }

    public static ProfileRequest buildProfileRequest() {
        return ProfileRequest.builder()
                .name("Alex")
                .industryUuid(UUID.randomUUID().toString())
                .agreement(true)
                .build();
    }

    public static Profile buildProfile() {
        return Profile.builder()
                .uuid(UUID.randomUUID())
                .name("User")
                .industry(buildIndustry())
                .agreement(true)
                .build();

    }

    public static ProfileResponse buildProfileResponse() {
        return ProfileResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .name("Alex")
                .industryUuid(UUID.randomUUID().toString())
                .agreement(true)
                .build();
    }
}