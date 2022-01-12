package com.nsia.officems.taqnin.doc_department.dto;

import com.nsia.officems._admin.department.Department;
import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.taqnin.doc_department.DocDepartment;
import com.nsia.officems.taqnin.document.Document;
import com.nsia.officems.taqnin.document.DocumentService;

public class DocDepartmentMapper {

    public static DocDepartment docDepartmentMapDto(DocDepartment department, DepartmentService departmentService,
            UserService userService, DocumentService documentService, DocDepartmentDto dto) {
        DateTimeChange changeDate = new DateTimeChange();
        department.setDeleted(false);
        department.setReceiveDate(
                dto.getReceiveDate() == null ? null : changeDate.convertPersianDateToGregorianDate(dto.getReceiveDate()));
        department.setDescription(dto.getDescription());
        Department dep = departmentService.findById(dto.getDepartment_id());
        department.setDepartment(dep);

        Document document = documentService.findById(dto.getDocument_id());
        department.setDocument(document);

        User user = userService.findById(dto.getUser_id());
        department.setUser(user);

        return department;
    }
}
