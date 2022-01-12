package com.nsia.officems.gop.panelty.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaneltyDto {
    private Long id;
    private Long type;
    private Long status;
    private Long profile;
    private Long profileJob;
    private String suggestedNumber;
    private String suggestedDate;
    private String suggestedSource;
    private String decreeNumber;
    private String decreeDate;
    private String degreeSource;
    private Long position;
    private String positionTitle;
    private Long ministry;
    private Long authority;
    private Long commission;
    private Long militaryPosition;

    
}
