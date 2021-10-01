package com.solbeg.sectorsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FormResponse {
    private Long id;
    private String username;
    private Long sectorId;
    private Boolean agreement;
}