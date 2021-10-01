package com.solbeg.sectorsapp.dto;

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
public class FormRequest {
    @NotBlank(message = "Username should not be null or blank")
    private String username;
    @NotNull(message = "Sector should not be null")
    private Long sector;
    @NotNull(message = "Agreement should not be null")
    private Boolean agreement;
}