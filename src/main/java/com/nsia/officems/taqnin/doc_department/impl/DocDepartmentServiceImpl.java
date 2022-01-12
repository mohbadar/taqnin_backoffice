package com.nsia.officems.taqnin.doc_department.impl;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.taqnin.doc_department.DocDepartment;
import com.nsia.officems.taqnin.doc_department.DocDepartmentRepository;
import com.nsia.officems.taqnin.doc_department.DocDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocDepartmentServiceImpl implements DocDepartmentService {
    @Autowired
    private DocDepartmentRepository docDepartmentRepository;

    @Autowired
    UserService userService;

    @Autowired
    DepartmentService departmentService;

    @Override
    public List<DocDepartment> findByDocumentId(Long id) {
        return docDepartmentRepository.findByDocument_idAndDeletedFalse(id);
    }

    @Override
    public DocDepartment findById(Long id) {
        Optional<DocDepartment> optionalObj = docDepartmentRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public DocDepartment create(DocDepartment docDepartment) {
        return docDepartmentRepository.save(docDepartment);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<DocDepartment> oDepartment = docDepartmentRepository.findById(id);

        if (oDepartment.isPresent()) {
            DocDepartment department = oDepartment.get();
            department.setDeleted(true);
            department.setDeletedBy(userService.getLoggedInUser().getUsername());
            department.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            docDepartmentRepository.save(department);
            return true;
        }

        return false;
    }

    @Override
    public DocDepartment update(Long id, DocDepartment department) {
        if (id != null) {
            Optional<DocDepartment> newDepartment = docDepartmentRepository.findById(id);
            if (newDepartment.isPresent()) {
                DocDepartment updatedDocDepartment = newDepartment.get();
                updatedDocDepartment.setReceiveDate(department.getReceiveDate());
                return docDepartmentRepository.save(updatedDocDepartment);
            }
            return null;

        }

        return null;
    }
}
