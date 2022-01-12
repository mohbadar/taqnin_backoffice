package com.nsia.officems.gop.profile_retirement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResignationDto {
    private Long id;
    private String maktubNumber;
    private String decreeNumber;
    private String decreeDate;
    private String maktubDate;
    private Long type;
    private String detail;
    private Long status;
    private Long profile;
    private Long profileJob;
}
