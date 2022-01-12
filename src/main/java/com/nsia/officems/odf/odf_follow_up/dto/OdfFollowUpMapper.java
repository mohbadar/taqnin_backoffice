package com.nsia.officems.odf.odf_follow_up.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.odf.odf_follow_up.OdfFollowUp;
import com.nsia.officems.odf.odf_follow_up_type.OdfFollowUpTypeService;
import com.nsia.officems.odf.odf_order.OdfOrderService;

public class OdfFollowUpMapper {
    public static OdfFollowUp MapProposalDto(OdfFollowUp follow,OdfFollowUpDto dto, OdfOrderService orderService, OdfFollowUpTypeService typeService ){ 
        DateTimeChange changeDate = new DateTimeChange();

        try{
            follow.setActive(true);
            follow.setDate(dto.getDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getDate()));
            follow.setType(dto.getType() == null? null: typeService.findById(dto.getType()));
            follow.setTitle(dto.getTitle() == null? null: dto.getTitle());
            follow.setSummary(dto.getSummary() == null? null: dto.getSummary());
            follow.setOrder(dto.getOrder() == null? null: orderService.findById(dto.getOrder()));
            return follow;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    } 
}
