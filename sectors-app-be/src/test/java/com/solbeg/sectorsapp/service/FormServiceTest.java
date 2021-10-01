package com.solbeg.sectorsapp.service;

import com.solbeg.sectorsapp.dto.FormRequest;
import com.solbeg.sectorsapp.dto.FormResponse;
import com.solbeg.sectorsapp.entity.Form;
import com.solbeg.sectorsapp.entity.Sector;
import com.solbeg.sectorsapp.mapper.FormMapper;
import com.solbeg.sectorsapp.repository.FormRepository;
import com.solbeg.sectorsapp.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class FormServiceTest {

    @MockBean
    private FormRepository formRepository;
    @MockBean
    private SectorService sectorService;
    @MockBean
    private FormMapper formMapper;

    @Autowired
    private FormService formService;

    @Test
    public void shouldSaveForm() {
        //given
        FormRequest formRequest = TestUtil.buildFormRequest();
        Form form = TestUtil.buildForm();
        FormResponse formResponse = TestUtil.buildFormResponse();
        Sector sector = TestUtil.buildSector();

        when(sectorService.findSectorById(eq(formRequest.getSector()))).thenReturn(sector);
        when(formMapper.requestToModel(eq(formRequest))).thenReturn(form);
        when(formRepository.save(eq(form))).thenReturn(form);
        when(formMapper.modelToResponse(eq(form))).thenReturn(formResponse);

        //when
        FormResponse result = formService.save(formRequest);

        //then
        assertThat(result, equalTo(formResponse));
    }

    @Test
    public void shouldUpdateForm() {
        //given
        Long id = 54L;
        FormRequest formRequest = TestUtil.buildFormRequest();
        Form form = TestUtil.buildForm();
        FormResponse formResponse = TestUtil.buildFormResponse();
        Sector sector = TestUtil.buildSector();

        when(formRepository.findById(eq(id))).thenReturn(Optional.of(form));
        when(formRepository.save(eq(form))).thenReturn(form);
        when(formMapper.modelToResponse(eq(form))).thenReturn(formResponse);

        //when
        FormResponse result = formService.update(id, formRequest);

        //then
        assertThat(result, equalTo(formResponse));
    }
}