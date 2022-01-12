package com.nsia.officems.taqnin.taqnin_resolution.dto;

import com.nsia.officems._admin.shura.ShuraService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.taqnin.taqnin_resolution.TaqninResolution;

public class TaqninResolutionMapper {
    public static TaqninResolution MapResolutionDto(TaqninResolution resolution, TaqninResolutionDto dto,
            ShuraService shuraService) {
        DateTimeChange changeDate = new DateTimeChange();

        try {
            resolution.setActive(true);
            resolution.setResolutionNumber(dto.getResolutionNumber());
            resolution.setResolutionDate(dto.getResolutionDate() == null ? null
                    : changeDate.convertPersianDateToGregorianDate(dto.getResolutionDate()));
            resolution.setShura(dto.getShuraId() == null ? null : shuraService.findById(dto.getShuraId()));
            resolution.setComponents(dto.getComponents());
            return resolution;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }
    }

}
