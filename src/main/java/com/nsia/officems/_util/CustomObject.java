package com.nsia.officems._util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.apache.catalina.mapper.Mapper;
public class CustomObject {

    public static ObjectNode getObject(double lat, double longi, String title, long count){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("lat", lat);
        node.put("long", longi);
        node.put("title", title);
        node.put("count", count);
        return node;

    }
    
}
