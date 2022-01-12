package com.nsia.officems._util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.Column;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Component;


@Component
public class DataTablesUtil {

    @PersistenceContext
    private EntityManager entityManager;


    public Object getDataList(String tableName, String sIndexColumn, String joinClause, String whereClause,
            String groupByClause, DataTablesInput input) {
        return getDataList(tableName, sIndexColumn, joinClause, whereClause,
         groupByClause, input, false);
    }
    
    // TODO: envSlug should be object as envSlug value and envSlug Column name
    public Object getDataList(String tableName, String sIndexColumn, String joinClause, String whereClause,
            String groupByClause, DataTablesInput input, boolean resultsetColSmallCase) {
        input.getColumns();

        if (tableName == null)
            tableName = "";
        if (sIndexColumn == null)
            sIndexColumn = "id";

        List<Column> aColumnsList = input.getColumns();
        List<String> aColumns = new ArrayList<>();
        for (Column aCol : aColumnsList) {
            aColumns.add(aCol.getData());
        }

        List<String> aColumnsAlises = new ArrayList<>();
        for (Column aCol : aColumnsList) {
            aColumnsAlises.add(aCol.getName());
        }

        if (aColumns.size() <= 0) {
            aColumns = new ArrayList<>();
            aColumns.add("id");
            aColumnsAlises.add("id");
        }

        /* Total data set length */
        String sQuery = "SELECT COUNT('" + sIndexColumn + "') AS row_count FROM " + tableName;
        sQuery += " " + joinClause;
        String yQuery = sQuery;

        System.out.println("sQuery before where:" + sQuery);
        System.out.println(" where clause value is:" + whereClause);

        String sQueryWhere = "";
        if (whereClause != "" && !whereClause.equalsIgnoreCase(null)) {
            // sQueryWhere += " WHERE " + whereClause;
            System.out.println("sQueryWhere is:" + sQueryWhere);
            System.out.println("INSIDE IF CLAUS:" + whereClause);
        }

        sQuery += sQueryWhere + " " + groupByClause;        
        System.out.println("sQuery: " + sQuery);

        // Query q = entityManager.createNativeQuery(sQuery);
        // BigInteger iTotal = BigInteger.valueOf(0);
        // if (groupByClause.length() > 0) {
        //     List<Tuple> aRows = q.getResultList();
        //     iTotal = BigInteger.valueOf(aRows.size());
        // } else {
        //     iTotal = (BigInteger) q.getSingleResult();
        // }

        /*
         * Paging
         */
        String sLimit = "";
        Integer iDisplayStart = input.getStart();
        Integer iDisplayLength = input.getLength();
        if (iDisplayStart != null && iDisplayLength != -1) {
            sLimit = "LIMIT " + iDisplayLength + " offset " + iDisplayStart;
        }

        System.out.println("sLimit: " + sLimit);

        // $uri_string = $_SERVER['QUERY_STRING'];
        // $uri_string = preg_replace("/\%5B/", '[', $uri_string);
        // $uri_string = preg_replace("/\%5D/", ']', $uri_string);

        /*
         * Ordering
         */
        String sOrder = " ORDER BY ";
        // $sOrderIndex = $arr['order[0][column]'];
        Integer sOrderIndex = input.getOrder().get(0).getColumn();
        // $sOrderDir = $arr['order[0][dir]'];
        String sOrderDir = input.getOrder().get(0).getDir();
        // $bSortable_ = $arr_columns['columns[' . $sOrderIndex . '][orderable]'];
        Boolean bSortable_ = input.getColumns().get(sOrderIndex).getOrderable();
        if (bSortable_ == true) {
            sOrder += getBeforeAsKeyword(aColumns.get(sOrderIndex)) + (sOrderDir.equals("asc") ? " asc " : " desc ");
        }

        System.out.println("sOrder: " + sOrder);

        /*
         * Filtering
         */
        String sWhere = "";
        // $sSearchVal = $arr['search[value]'];
        String sSearchVal = input.getSearch().getValue();
        if (sSearchVal != null && sSearchVal != "") {
            sSearchVal = sSearchVal.toLowerCase();
            sWhere = "WHERE (";
            for (int i = 0; i < aColumns.size(); i++) {
                Boolean bSearchable_ = input.getColumns().get(i).getSearchable();
                if (bSearchable_ != null && bSearchable_ == true) {
                    // TODO: we have to escape the sSearchVal to avoid SQL Injection
                    sWhere += "CAST(" + getBeforeAsKeyword(input.getColumns().get(i).getData()) + " as text) LIKE '%"
                            + sSearchVal + "%' OR ";
                }
            }
            sWhere = sWhere.substring(0, sWhere.length() - 3);
            sWhere += ')';
        }

        System.out.println("sWhere: " + sWhere);

        Boolean isASearch = false;
        /* Individual column filtering */
        // $sSearchReg = $arr['search[regex]'];
        // Boolean sSearchReg = input.getSearch().getRegex();
        for (int i = 0; i < aColumns.size(); i++) {
            // $bSearchable_ = $arr['columns[' . $i . '][searchable]'];
            Boolean bSearchable_ = input.getColumns().get(i).getSearchable();
            Boolean sSearchReg = input.getColumns().get(i).getSearch().getRegex();
            if (bSearchable_ != null && bSearchable_ == true && sSearchReg != false) {
                // $search_val = $arr['columns[' . $i . '][search][value]'];
                String search_val = input.getColumns().get(i).getSearch().getValue();
                if (search_val != "" && !search_val.equals("") && !search_val.equals("null")) {

                    if (sWhere.equals("")) {
                        sWhere = " WHERE ";
                    } else {
                        sWhere += " AND ";
                    }
                    isASearch = true;
                }
                // TODO: we have to escape the sSearchVal to avoid SQL Injection
                sWhere += "CAST(" + getBeforeAsKeyword(aColumns.get(i)) + " as text) LIKE '%" + search_val + "%' ";
            }
        }
        // if (isASearch) {
        //     if (whereClause.length() > 0) {
        //         sWhere += " AND " + whereClause;
        //     }
        // }

        System.out.println("sWhere: " + sWhere);

        /*
         * SQL queries Get data to display
         */
        String comma_seperated_columns = String.join(",", aColumns);
        sQuery = "SELECT " + comma_seperated_columns.replaceAll(" , ", " ") + " FROM " + tableName + " ";
        sQuery += joinClause;
        // only in searching
        System.out.println("--------------------- Where Clause:: =============== " + whereClause);
        if (whereClause != "" && !whereClause.equals(null)) {
            if(sWhere != ""){
                sQuery += sWhere + " AND " + whereClause;
                yQuery += sWhere + " AND " + whereClause;
            }
            else{
                sQuery += " WHERE " + sWhere + " " + whereClause;
                yQuery += " WHERE " + sWhere + " " + whereClause;
            }
            
        } else {
            sQuery += " " + sWhere;
            yQuery += " " + sWhere;
        }

        System.out.println("sQuery: " + yQuery);
        Query y = entityManager.createNativeQuery(yQuery);
        BigInteger iTotal = BigInteger.valueOf(0);
        if (groupByClause.length() > 0) {
            List<Tuple> aRows = y.getResultList();
            iTotal = BigInteger.valueOf(aRows.size());
        } else {
            iTotal = (BigInteger) y.getSingleResult();
        }

        sQuery += groupByClause;
        sQuery += sOrder;
        sQuery += sLimit;

        System.out.println("sQuery: " + sQuery);
        

        // log_message('debug', 'datatables query: ' . $sQuery);
        Query rResult = entityManager.createNativeQuery(sQuery, Tuple.class);

        /*
         * Output
         */
        Integer sEcho = input.getDraw();
        Map<String, Object> output = new LinkedHashMap<String, Object>();
        output.put("draw", sEcho);
        output.put("recordsTotal", iTotal);

        List<Tuple> aRows = rResult.getResultList();

        int iFilteredTotal = aRows.size();
        output.put("recordsFiltered", iFilteredTotal);

        List<Map> result = new ArrayList<>(aRows.size());
        for (Tuple aRow : aRows) {
            Map<String, Object> row = new LinkedHashMap<String, Object>();
            for (String col : aColumnsAlises) {
                if(resultsetColSmallCase) {
                    row.put(col.toLowerCase(), aRow.get(col));
                } else {
                    row.put(col, aRow.get(col));
                }
            }
            result.add(row);
        }

        if (result.size() > 0) {
            output.put("data", result);
        } else {
            output.put("data", new ArrayList<>());
        }

        return output;
    }

    public String getBeforeAsKeyword(String columnName) {
        if (columnName.contains(" as ")) {
            return columnName.split(" as ")[0];
        } else if (columnName.contains(" AS ")) {
            return columnName.split(" AS ")[0];
        }
        return columnName;
    }

    public String whereClause(Map<String, String> filters) {
        List<String> filterList = new ArrayList<String>();
        String whereClause = "";
        if (filters != null) {
            filters.forEach((k, v) -> {
                if (k == "from_date") {
                    filterList.add(" AND created_at < Date('" + v + "')");
                } else if (k == "to_date") {
                    filterList.add(" AND created_at > Date('" + v + "')");
                } else {
                    filterList.add(" AND " + k + " = '" + v + "'");
                }
            });
            for (int i = 0; i < filterList.size(); i++) {
                whereClause += filterList.get(i);
            }
        }

        if (whereClause.length() > 0) {
            whereClause = whereClause.replaceFirst(" AND ", "");
        }

        return whereClause;
    }
}