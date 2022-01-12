package com.nsia.officems.gop.mark_Medal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedalDto {

    private Long id;
    private String medalType;
    private String approvedSource;
    private String maktubNumber;
    private String maktubDate;
    private Long profile;
    
}
