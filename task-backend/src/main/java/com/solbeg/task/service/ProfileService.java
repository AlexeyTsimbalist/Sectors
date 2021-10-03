package com.solbeg.task.service;

import com.solbeg.task.dto.ProfileRequest;
import com.solbeg.task.dto.ProfileResponse;
import com.solbeg.task.entity.Profile;
import com.solbeg.task.entity.Industry;
import com.solbeg.task.exception.NoSuchResourceException;
import com.solbeg.task.mapper.ProfileMapper;
import com.solbeg.task.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final IndustryService industryService;
    private final ProfileMapper profileMapper;

    public ProfileResponse save(ProfileRequest profileRequest) {
        Industry industry = industryService.findIndustryByUuid(UUID.fromString(profileRequest.getIndustryUuid()));

        Profile profile = profileMapper.requestToModel(profileRequest);
        profile.setIndustry(industry);

        Profile savedProfile = profileRepository.save(profile);

        return profileMapper.modelToResponse(savedProfile);
    }

    public ProfileResponse update(String profileUuid, ProfileRequest profileRequest) {
        Profile existingProfile = findProfileByUuid(UUID.fromString(profileUuid));
        Industry industry = industryService.findIndustryByUuid(UUID.fromString(profileRequest.getIndustryUuid()));

        profileMapper.requestToModel(profileRequest, existingProfile);
        existingProfile.setIndustry(industry);

        existingProfile = profileRepository.save(existingProfile);

        return profileMapper.modelToResponse(existingProfile);
    }

    private Profile findProfileByUuid(UUID uuid) {
        return profileRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchResourceException("Form", uuid));
    }
}