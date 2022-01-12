package com.nsia.officems.taqnin.summary.impl;

import com.nsia.officems.taqnin.document.DocumentService;
import com.nsia.officems.taqnin.summary.SummaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

import java.util.HashMap;
import java.util.Map;

import com.nsia.officems._util.DataTablesUtil;
import org.springframework.stereotype.Service;

@Service
public class SummaryServiceImpl implements SummaryService{
    @Autowired
    private DocumentService documentService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public Map<String, Object> countDocuments(){
        Map<String, Object> data = new HashMap<String, Object>();

        long total = documentService.countTotalDocuments();
        long notApproved = documentService.countNotApprovedDocuments();
        long approved = documentService.countApprovedDocuments();
         long inProgress = 0; //documentService.countInProgressDocuments();

        data.put("total", total);
        data.put("approved", approved);
        data.put("notApproved", notApproved);
         data.put("inProgress", inProgress);

        return data;
    }

    
}
