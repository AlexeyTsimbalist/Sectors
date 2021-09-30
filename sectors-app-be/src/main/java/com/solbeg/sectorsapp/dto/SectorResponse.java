package com.solbeg.sectorsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SectorResponse {
    private Long id;
    private String name;
    private List<SectorResponse> children;
}