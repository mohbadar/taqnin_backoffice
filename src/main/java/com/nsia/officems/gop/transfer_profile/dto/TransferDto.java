package com.nsia.officems.gop.transfer_profile.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferDto {
    private Long id;
    private String maktubNumber;
    private String maktubDate;
    private Long position;
    private String positionTitle;
    private Long ministry;
    private Long authority;
    private Long commission;
    private Long profile;
    private Long profileJob;
    private Long status;
    private Long militaryPosition;
}
