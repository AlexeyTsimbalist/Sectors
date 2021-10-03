package com.solbeg.task.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbeg.task.dto.IndustryResponse;
import com.solbeg.task.service.IndustryService;
import com.solbeg.task.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IndustryControllerTest {

    private static final String URI_PATH = "/api/industry";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IndustryService industryService;

    @Test
    public void shouldFindAllIndustries() throws Exception {
        //given
        IndustryResponse industryResponse1 = TestUtil.buildIndustryResponse();
        IndustryResponse industryResponse2 = TestUtil.buildIndustryResponse();
        List<IndustryResponse> allIndustries = List.of(industryResponse1, industryResponse2);

        when(industryService.findAll()).thenReturn(allIndustries);

        //then
        MvcResult mvcResult = mockMvc.perform(get(URI_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<IndustryResponse> result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        assertThat(result, equalTo(allIndustries));
    }
}