package com.nsia.officems.gop.decree_individual.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecreeIndividualDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Boolean deleted;
    private Long decreeIndividualNo;

}
