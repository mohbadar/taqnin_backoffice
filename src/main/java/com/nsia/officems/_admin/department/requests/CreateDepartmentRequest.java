package com.nsia.officems._admin.department.requests;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateDepartmentRequest {

    @NotNull
    private String namePs;

    @NotNull
    private String nameDr;

    @NotNull
    private String nameEn;
}
