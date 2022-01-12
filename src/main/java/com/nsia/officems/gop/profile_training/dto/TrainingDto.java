package com.nsia.officems.gop.profile_training.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingDto {
    private Long id;
    private String type;
    private String title;
    private String startDate;
    private String endDate;
    private String seminarType;
    private Long province;
    private Long country;
    private Long profile;
}
