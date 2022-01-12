package com.nsia.officems.taqnin.taqnin_resolution.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaqninResolutionDto {
    private String resolutionNumber;
    private String resolutionDate;
    private Long shuraId;
    private String components;
}
