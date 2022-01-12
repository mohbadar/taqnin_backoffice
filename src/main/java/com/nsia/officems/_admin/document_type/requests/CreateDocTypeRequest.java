package com.nsia.officems._admin.document_type.requests;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateDocTypeRequest {

    @NotNull
    private String namePs;

    @NotNull
    private String nameDr;

    @NotNull
    private String nameEn;
}
