package com.nsia.officems.odf.odf_order.dto;

import java.util.HashSet;
import java.util.Set;

import com.nsia.officems._admin.department.Department;
import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreementService;
import com.nsia.officems.odf.odf_order.OdfOrder;
import com.nsia.officems.odf.odf_subject.OdfSubjectService;

import org.hibernate.criterion.Example.PropertySelector;

public class OdfOrderMapper {
    public static OdfOrder MapOrderDto(OdfOrder order, OdfOrderDto dto, OdfSubjectService odfSubjectService,
            DepartmentService departmentService) {
        DateTimeChange changeDate = new DateTimeChange();

        try {
            order.setOrderNumber(dto.getOrderNumber());
            order.setOrderDate(dto.getOrderDate() == null ? null
                    : changeDate.convertPersianDateToGregorianDate(dto.getOrderDate()));
            order.setSummary(dto.getSummary());
            order.setImplementationEndDate(dto.getImplementationEndDate() == null ? null
                    : changeDate.convertPersianDateToGregorianDate(dto.getImplementationEndDate()));

            if (dto.getImplementingDepartmentsIds() != null) {
                Set<Department> implementingDepartments = new HashSet<>();
                dto.getImplementingDepartmentsIds().forEach((id) -> {
                    implementingDepartments.add(departmentService.findById(id));
                });
                order.setImplementingDepartments(implementingDepartments);
            }

            if (dto.getAssistingDepartmentsIds() != null) {
                Set<Department> assistingDepartments = new HashSet<>();
                dto.getAssistingDepartmentsIds().forEach((id) -> {
                    assistingDepartments.add(departmentService.findById(id));
                });
                order.setAssistingDepartments(assistingDepartments);
            }

            order.setSubject(dto.getSubjectId() == null ? null : odfSubjectService.findById(dto.getSubjectId()));
            return order;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
