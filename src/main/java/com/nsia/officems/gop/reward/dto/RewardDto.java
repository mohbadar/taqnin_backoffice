package com.nsia.officems.gop.reward.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RewardDto {
    private Long id;
    private Long type;
    private Long profile;
    private Long profileJob;
    private String suggestedNumber;
    private String suggestedDate;
    private String suggestedSource;
    private String decreeNumber;
    private String decreeDate;
    private String degreeSource;
    private String cashAmount;
    private String appreciationDegree;
    
}
