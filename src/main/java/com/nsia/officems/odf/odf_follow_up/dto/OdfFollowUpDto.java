package com.nsia.officems.odf.odf_follow_up.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OdfFollowUpDto {
    private String date;
    private String title;
    private String summary;
    private Long type;
    private Long order;
}
