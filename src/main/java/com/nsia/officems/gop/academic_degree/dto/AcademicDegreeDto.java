package com.nsia.officems.gop.academic_degree.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcademicDegreeDto {
    private Long id;
    private String shumara;
    private String title;
    private String date;
    private Long type;
    private Long profile;  
}
