package com.nsia.officems.gop.profile_fired.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileFiredDto {
    private Long id;
    private Long type;
    private Long status;
    private String maktubNumber;
    private String maktubDate;
    private Long profileJob;
    private Long profile;
}
