package com.solbeg.task.mapper;

import com.solbeg.task.dto.ProfileRequest;
import com.solbeg.task.dto.ProfileResponse;
import com.solbeg.task.entity.Profile;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileMapper {

    private final ModelMapper modelMapper;

    public Profile requestToModel(ProfileRequest profileRequest) {
        return modelMapper.map(profileRequest, Profile.class);
    }

    public void requestToModel(ProfileRequest profileRequest, Profile profile) {
        modelMapper.map(profileRequest, profile);
    }

    public ProfileResponse modelToResponse(Profile profile) {
        return modelMapper.map(profile, ProfileResponse.class);
    }
}