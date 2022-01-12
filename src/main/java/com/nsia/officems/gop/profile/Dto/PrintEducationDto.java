package com.nsia.officems.gop.profile.Dto;

import java.util.Date;

import com.nsia.officems._admin.country.Country;
import com.nsia.officems.gop.education_level.EducationLevel;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile_job.ProfileJob;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrintEducationDto {
    private Long id;

    private String startDateString;

    private String graduationDateString;

    private Date startDate;

    private Date graduationDate;

    private String duration;
    private String fieldOfStudy;
    private String university;
    private String universityType;
    private Boolean insideWork;

    private Country country;

    private EducationLevel level;

    private Profile profile;

    private ProfileJob profileJob;
}
