package com.solbeg.task.controller;

import com.solbeg.task.dto.ProfileRequest;
import com.solbeg.task.dto.ProfileResponse;
import com.solbeg.task.service.ProfileService;
import com.solbeg.task.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.solbeg.task.util.TestUtil.convertObjectToJson;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProfileControllerTest {

    private static final String URI_PATH = "/api/profile";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    @Test
    public void shouldSaveProfile() throws Exception {
        //given
        ProfileRequest profileRequest = TestUtil.buildProfileRequest();
        ProfileResponse profileResponse = TestUtil.buildProfileResponse();

        when(profileService.save(eq(profileRequest))).thenReturn(profileResponse);

        //then
        mockMvc.perform(post(URI_PATH)
                        .content(convertObjectToJson(profileRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid", is(profileResponse.getUuid())))
                .andExpect(jsonPath("$.name", is(profileResponse.getName())))
                .andExpect(jsonPath("$.industryUuid", is(profileResponse.getIndustryUuid())))
                .andExpect(jsonPath("$.agreement", is(profileResponse.getAgreement())));
    }

    @Test
    public void shouldNotSaveProfile() throws Exception {
        //given
        ProfileRequest profileRequest = TestUtil.buildProfileRequest();
        profileRequest.setName(null);

        //then
        mockMvc.perform(post(URI_PATH)
                        .content(convertObjectToJson(profileRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldUpdateProfile() throws Exception {
        //given
        String uuid = UUID.randomUUID().toString();
        ProfileRequest profileRequest = TestUtil.buildProfileRequest();
        ProfileResponse profileResponse = TestUtil.buildProfileResponse();

        when(profileService.update(eq(uuid), eq(profileRequest))).thenReturn(profileResponse);

        //then
        mockMvc.perform(put(URI_PATH + "/" + uuid)
                        .content(convertObjectToJson(profileRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(profileResponse.getUuid())))
                .andExpect(jsonPath("$.name", is(profileResponse.getName())))
                .andExpect(jsonPath("$.industryUuid", is(profileResponse.getIndustryUuid())))
                .andExpect(jsonPath("$.agreement", is(profileResponse.getAgreement())));
    }
}