package com.solbeg.sectorsapp.service;

import com.solbeg.sectorsapp.dto.SectorResponse;
import com.solbeg.sectorsapp.entity.Sector;
import com.solbeg.sectorsapp.mapper.SectorMapper;
import com.solbeg.sectorsapp.repository.SectorRepository;
import com.solbeg.sectorsapp.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class SectorServiceTests {

    @MockBean
    private SectorRepository sectorRepository;
    @MockBean
    private SectorMapper sectorMapper;

    @Autowired
    private SectorService sectorService;

    @Test
    public void shouldFindAllSectors() {
        //given
        Sector sector1 = TestUtil.buildSector();
        Sector sector2 = TestUtil.buildSector();
        List<Sector> allSectors = List.of(sector1, sector2);

        SectorResponse sectorResponse = TestUtil.buildSectorResponse();

        when(sectorRepository.findAllByParentIsNull()).thenReturn(allSectors);
        when(sectorMapper.modelToResponse(any())).thenReturn(sectorResponse);

        //when
        List<SectorResponse> result = sectorService.findAll();

        //then
        assertThat(result, hasSize(2));
    }

    @Test
    public void shouldFindSectorById() {
        //given
        Long id = 34L;
        Sector sector = TestUtil.buildSector();

        when(sectorRepository.findById(eq(id))).thenReturn(Optional.of(sector));

        //when
        Sector result = sectorService.findSectorById(id);

        //then
        assertThat(result, equalTo(sector));
    }
}