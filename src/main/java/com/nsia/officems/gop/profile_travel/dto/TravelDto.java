package com.nsia.officems.gop.profile_travel.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelDto {
    private Long id;
    private String MaktubNo;
    private String date;
    private Long profileJob;
    private String purpose;
    private String type;
    private Long province;
    private Long country;
    private Long profile; 
}
