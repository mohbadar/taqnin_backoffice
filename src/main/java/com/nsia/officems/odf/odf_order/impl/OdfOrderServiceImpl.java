package com.nsia.officems.odf.odf_order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreementService;
import com.nsia.officems.odf.odf_order.OdfOrder;
import com.nsia.officems.odf.odf_order.OdfOrderRepository;
import com.nsia.officems.odf.odf_order.OdfOrderService;
import com.nsia.officems.odf.odf_order.dto.OdfOrderDto;
import com.nsia.officems.odf.odf_order.dto.OdfOrderMapper;
import com.nsia.officems.odf.odf_subject.OdfSubjectService;

import org.springframework.stereotype.Service;

@Service
public class OdfOrderServiceImpl implements OdfOrderService {
    @Autowired
    private OdfOrderRepository odfOrderRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private OdfSubjectService odfSubjectService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public List<OdfOrder> findAll() {
        return odfOrderRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "";
        // To have first AND with no error
        String whereClause = " ord.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.odf_order ord", null, joinClause, whereClause, groupByClause,
                input);
    }

    @Override
    public OdfOrder findById(Long id) {
        Optional<OdfOrder> optionalObj = odfOrderRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public OdfOrder create(OdfOrder odfOrder) {
        odfOrder.setDeleted(false);
        return odfOrderRepository.save(odfOrder);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<OdfOrder> orderToBeDeleted = odfOrderRepository.findById(id);

        if (orderToBeDeleted.isPresent()) {
            OdfOrder getOrderToBeDeleted = orderToBeDeleted.get();
            getOrderToBeDeleted.setDeleted(true);
            getOrderToBeDeleted.setDeletedBy(userService.getLoggedInUser().getUsername());
            getOrderToBeDeleted.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            odfOrderRepository.save(getOrderToBeDeleted);
            return true;
        }

        return false;
    }

    @Override
    public Boolean update(Long id, OdfOrderDto dto) {
        Optional<OdfOrder> odfOrder = odfOrderRepository.findById(id);
        if (odfOrder.isPresent()) {
            OdfOrder odfOrderToBeUpdated = odfOrder.get();
            OdfOrderMapper.MapOrderDto(odfOrderToBeUpdated, dto, odfSubjectService, departmentService);
            odfOrderToBeUpdated.setUpdatedBy(userService.getLoggedInUser().getUsername());
            odfOrderToBeUpdated.setUpdatedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            odfOrderRepository.save(odfOrderToBeUpdated);
            return true;
        }
        return false;
    }

    @Override
    public List<OdfOrder> findBySubjectId(Long id) {
        return odfOrderRepository.findBySubject_id(id);
    }
}
