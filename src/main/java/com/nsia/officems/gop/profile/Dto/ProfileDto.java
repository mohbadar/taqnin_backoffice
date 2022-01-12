package com.nsia.officems.gop.profile.Dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
    private Long id;
    private String profileCode;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String grandFatherName;
    private Long ethnic;
    private Long nationality;
    private Long religion;
    private Long sect;
    private String blood;
    private String dob;
    private String dobGregorian;
    private Long birthCountry;
    private Long birthProvince;
    private Long birthDistrict;
    private String birthVillage;
    private Long originalCountry;
    private Long originalProvince;
    private Long originalDistrict;
    private String originalVillage;
    private Long currentCountry;
    private Long currentProvince;
    private Long currentDistrict;
    private String currentVillage;
    private String tazkiraNumber;
    private String tazkiraTog;
    private String tazkiraRegister;
    private String tazkiraPage;
    private String tazkiraDate;
    private String tazkiraPlace;
    private String enid;
    private String appointType;
    private List<Long> language;
    private List<Long> nationalLanguages;
    private String phone;
    private String year;
    private String email;
    private String appointDate;
    private String decreeNumber;
    private String positionTitle;
    private Long position;
    private Long grade;
    private Long status;
    private Integer qadamYear;
    private Integer qadamMonth;
    private Integer qadamDay;
    private Long ministry;
    private Long authority;
    private Long commission;
    private String suggestionNumber;
    private String suggestionDate;
    private Long gender;
    private String avatar;
    private Boolean disable;
    private Long militaryGrade;
    private Long militaryPosition;
    
}
