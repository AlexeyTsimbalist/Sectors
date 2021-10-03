package com.solbeg.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileRequest {
    @NotBlank(message = "Username should not be null or blank")
    private String name;
    @NotBlank(message = "IndustryUuid should not be blank")
    private String industryUuid;
    @NotNull(message = "Agreement should not be null")
    private Boolean agreement;
}