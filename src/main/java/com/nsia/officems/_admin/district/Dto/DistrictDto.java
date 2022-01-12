package com.nsia.officems._admin.district.Dto;

import java.io.File;
import java.util.Date;
import com.nsia.officems._admin.country.Country;
import org.dom4j.Text;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistrictDto {
    private Long id;
	private String code;
	private String namePs;
	private String nameDr;
	private String nameEn;
    private  Long province;
}

