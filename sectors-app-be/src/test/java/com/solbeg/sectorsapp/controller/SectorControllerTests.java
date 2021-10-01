package com.solbeg.sectorsapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbeg.sectorsapp.dto.SectorResponse;
import com.solbeg.sectorsapp.service.SectorService;
import com.solbeg.sectorsapp.util.TestUtil;
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
class SectorControllerTests {

    private static final String URI_PATH = "/api/sector";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SectorService sectorService;

    @Test
    public void shouldFindAllSectors() throws Exception {
        //given
        SectorResponse sectorResponse1 = TestUtil.buildSectorResponse();
        SectorResponse sectorResponse2 = TestUtil.buildSectorResponse();
        List<SectorResponse> allSectors = List.of(sectorResponse1, sectorResponse2);

        when(sectorService.findAll()).thenReturn(allSectors);

        //then
        MvcResult mvcResult = mockMvc.perform(get(URI_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<SectorResponse> result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        assertThat(result, equalTo(allSectors));
    }
}