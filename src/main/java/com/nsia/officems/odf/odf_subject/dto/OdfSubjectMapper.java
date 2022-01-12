package com.nsia.officems.odf.odf_subject.dto;

import com.nsia.officems.odf.odf_resolution.OdfResolutionService;
import com.nsia.officems.odf.odf_subject.OdfSubject;

public class OdfSubjectMapper {
    public static OdfSubject MapSubjectDto(OdfSubject subject, OdfSubjectDto dto,
            OdfResolutionService odfResolutionService) {

        try {
            subject.setActive(true);
            subject.setSubjectAbstract(dto.getSubjectAbstract());
            subject.setDetails(dto.getDetails());
            subject.setResolution(
                    dto.getResolutionId() == null ? null : odfResolutionService.findById(dto.getResolutionId()));
            return subject;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }
    }

}
