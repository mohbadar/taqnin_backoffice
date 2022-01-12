package com.nsia.officems.odf.odf_resolution.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OdfResolutionDto {
    private String resolutionNumber;
    private String resolutionDate;
    private Long shuraId;
    private String components;
}
