package com.nsia.officems.gop.profile_promotion.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfilePromotionDto {
    private Long id;
    private Integer qadamYear;
    private Integer qadamMonth;
    private Integer qadamDay;
    private String maktubNumber;
    private String maktubDate;
    private Long oldGrade;
    private Long newGrade;
    private Long profile;
    private Long profileJob;
    private Long type;
    private Long oldMilitaryGrade;
    private Long newMilitaryGrade;
}
