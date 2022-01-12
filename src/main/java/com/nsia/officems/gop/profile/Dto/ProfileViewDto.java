package com.nsia.officems.gop.profile.Dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileViewDto {
    private Long id;
    private String profileCode;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String grandFatherName;
    private String ethnic;
    private String nationality;
    private String religion;
    private String sect;
    private String blood;
    private String dob;
    private String dobGregorian;
    private String birthCountry;
    private String birthProvince;
    private String birthDistrict;
    private String birthVillage;
    private String originalCountry;
    private String originalProvince;
    private String originalDistrict;
    private String originalVillage;
    private String currentCountry;
    private String currentProvince;
    private String currentDistrict;
    private String currentVillage;
    private String tazkiraNumber;
    private String tazkiraTog;
    private String tazkiraRegister;
    private String tazkiraPage;
    private String tazkiraDate;
    private String tazkiraPlace;
    private String enid;
    private List<String> language;
    private List<String> nationalLanguages;
    private String phone;
    private String year;
    private String email;
    private String appointDate;
    private String decreeNumber;
    private String positionTitle;
    private String position;
    private String grade;
    private String status;
    private String appointType;
    private Integer qadamYear;
    private Integer qadamMonth;
    private Integer qadamDay;
    private String ministry;
    private String authority;
    private String commission;
    private String suggestionNumber;
    private String suggestionDate;
    private String gender;
    private String avatar;
    private Boolean approve;
    private String militaryGrade;
    private String militaryPosition;
}
