package com.solbeg.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileResponse {
    private String uuid;
    private String name;
    private String industryUuid;
    private Boolean agreement;
}