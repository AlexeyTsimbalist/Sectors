package com.solbeg.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class IndustryResponse {
    private String uuid;
    private String name;
    private List<IndustryResponse> children;
}