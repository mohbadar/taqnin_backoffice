package com.nsia.officems.gop.accountability.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Accountabilitydto {
    private Long id;
    private String startDate;
    private String endDate;
    private String personName;
    private Long position;
    private String positionTitle;
    private Long ministry;
    private Long authority;
    private Long commission;
    private Long profile;
    private Long militaryPosition;
}
