package com.solbeg.sectorsapp.controller;

import com.solbeg.sectorsapp.dto.FormRequest;
import com.solbeg.sectorsapp.dto.FormResponse;
import com.solbeg.sectorsapp.service.FormService;
import com.solbeg.sectorsapp.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.solbeg.sectorsapp.util.TestUtil.convertObjectToJson;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FormControllerTests {

    private static final String URI_PATH = "/api/form";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FormService formService;

    @Test
    public void shouldSaveForm() throws Exception {
        //given
        FormRequest formRequest = TestUtil.buildFormRequest();
        FormResponse formResponse = TestUtil.buildFormResponse();

        when(formService.save(eq(formRequest))).thenReturn(formResponse);

        //then
        mockMvc.perform(post(URI_PATH)
                        .content(convertObjectToJson(formRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(formResponse.getId())))
                .andExpect(jsonPath("$.username", is(formResponse.getUsername())))
                .andExpect(jsonPath("$.sectorId", is(formResponse.getSectorId().intValue())))
                .andExpect(jsonPath("$.agreement", is(formResponse.getAgreement())));
    }

    @Test
    public void shouldNotSave() throws Exception {
        //given
        FormRequest formRequest = TestUtil.buildFormRequest();
        formRequest.setUsername(null);
        FormResponse formResponse = TestUtil.buildFormResponse();

        //then
        mockMvc.perform(post(URI_PATH)
                        .content(convertObjectToJson(formRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldUpdateForm() throws Exception {
        //given
        Long formId = 62L;
        FormRequest formRequest = TestUtil.buildFormRequest();
        FormResponse formResponse = TestUtil.buildFormResponse();

        when(formService.update(eq(formId), eq(formRequest))).thenReturn(formResponse);

        //then
        mockMvc.perform(put(URI_PATH + "/" + formId)
                        .content(convertObjectToJson(formRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(formResponse.getId())))
                .andExpect(jsonPath("$.username", is(formResponse.getUsername())))
                .andExpect(jsonPath("$.sectorId", is(formResponse.getSectorId().intValue())))
                .andExpect(jsonPath("$.agreement", is(formResponse.getAgreement())));
    }
}