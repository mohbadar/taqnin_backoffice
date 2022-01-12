package com.nsia.officems.gop.academic_degree.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.gop.academic_degree_type.AcademicDecreeTypeService;
import com.nsia.officems.gop.academic_degree.AcademicDegree;
import com.nsia.officems.gop.profile.ProfileService;

public class AcademicDegreeMapperDto {
    public static AcademicDegree MapAcademicDto(AcademicDegree academicDegree, AcademicDegreeDto dto,
     ProfileService profileService, AcademicDecreeTypeService academicDecreeTypeService){ 
        DateTimeChange changeDate = new DateTimeChange();
        try{
            academicDegree.setActive(true);
            academicDegree.setShumara(dto.getShumara() == null? null: dto.getShumara());
            academicDegree.setTitle(dto.getTitle() == null? null: dto.getTitle());
            academicDegree.setDate(dto.getDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getDate()));
            academicDegree.setType(dto.getType() == null? null: academicDecreeTypeService.findById(dto.getType()));
            academicDegree.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            return academicDegree;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
