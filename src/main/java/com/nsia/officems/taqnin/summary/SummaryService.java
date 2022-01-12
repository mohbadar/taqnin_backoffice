package com.nsia.officems.taqnin.summary;

import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface SummaryService {
    public Map<String, Object> countDocuments();

}
