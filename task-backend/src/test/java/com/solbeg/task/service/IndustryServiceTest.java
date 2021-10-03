package com.solbeg.task.service;

import com.solbeg.task.dto.IndustryResponse;
import com.solbeg.task.entity.Industry;
import com.solbeg.task.mapper.IndustryMapper;
import com.solbeg.task.repository.IndustryRepository;
import com.solbeg.task.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class IndustryServiceTest {

    @MockBean
    private IndustryRepository industryRepository;
    @MockBean
    private IndustryMapper industryMapper;

    @Autowired
    private IndustryService industryService;

    @Test
    public void shouldFindAllIndustries() {
        //given
        Industry industry1 = TestUtil.buildIndustry();
        Industry industry2 = TestUtil.buildIndustry();
        List<Industry> allIndustries = List.of(industry1, industry2);

        IndustryResponse industryResponse = TestUtil.buildIndustryResponse();

        when(industryRepository.findAllByParentIsNull()).thenReturn(allIndustries);
        when(industryMapper.modelToResponse(any())).thenReturn(industryResponse);

        //when
        List<IndustryResponse> result = industryService.findAll();

        //then
        assertThat(result, hasSize(2));
    }

    @Test
    public void shouldFindIndustryByUuid() {
        //given
        String uuid = UUID.randomUUID().toString();
        Industry industry = TestUtil.buildIndustry();

        when(industryRepository.findById(eq(UUID.fromString(uuid)))).thenReturn(Optional.of(industry));

        //when
        Industry result = industryService.findIndustryByUuid(UUID.fromString(uuid));

        //then
        assertThat(result, equalTo(industry));
    }
}