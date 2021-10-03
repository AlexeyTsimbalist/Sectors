package com.solbeg.task.service;

import com.solbeg.task.dto.ProfileRequest;
import com.solbeg.task.dto.ProfileResponse;
import com.solbeg.task.entity.Industry;
import com.solbeg.task.entity.Profile;
import com.solbeg.task.mapper.ProfileMapper;
import com.solbeg.task.repository.ProfileRepository;
import com.solbeg.task.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProfileServiceTest {

    @MockBean
    private ProfileRepository profileRepository;
    @MockBean
    private IndustryService industryService;
    @MockBean
    private ProfileMapper profileMapper;

    @Autowired
    private ProfileService profileService;

    @Test
    public void shouldSaveForm() {
        //given
        ProfileRequest profileRequest = TestUtil.buildProfileRequest();
        Profile profile = TestUtil.buildProfile();
        ProfileResponse profileResponse = TestUtil.buildProfileResponse();
        Industry industry = TestUtil.buildIndustry();

        when(industryService.findIndustryByUuid(eq(UUID.fromString(profileRequest.getIndustryUuid())))).thenReturn(industry);
        when(profileMapper.requestToModel(eq(profileRequest))).thenReturn(profile);
        when(profileRepository.save(eq(profile))).thenReturn(profile);
        when(profileMapper.modelToResponse(eq(profile))).thenReturn(profileResponse);

        //when
        ProfileResponse result = profileService.save(profileRequest);

        //then
        assertThat(result, equalTo(profileResponse));
    }

    @Test
    public void shouldUpdateForm() {
        //given
        String uuid = UUID.randomUUID().toString();
        ProfileRequest profileRequest = TestUtil.buildProfileRequest();
        Profile profile = TestUtil.buildProfile();
        ProfileResponse profileResponse = TestUtil.buildProfileResponse();

        when(profileRepository.findById(eq(UUID.fromString(uuid)))).thenReturn(Optional.of(profile));
        when(profileRepository.save(eq(profile))).thenReturn(profile);
        when(profileMapper.modelToResponse(eq(profile))).thenReturn(profileResponse);

        //when
        ProfileResponse result = profileService.update(uuid, profileRequest);

        //then
        assertThat(result, equalTo(profileResponse));
    }
}