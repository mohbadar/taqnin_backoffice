package com.nsia.officems.odf.odf_order;

import java.util.List;
import java.util.Map;

import com.nsia.officems.odf.odf_order.dto.OdfOrderDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface OdfOrderService {
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public List<OdfOrder> findAll();

    public OdfOrder findById(Long id);

    public OdfOrder create(OdfOrder odfOrder);

    public Boolean delete(Long id);

    public Boolean update(Long id, OdfOrderDto dto);

    public List<OdfOrder> findBySubjectId(Long id);
}
