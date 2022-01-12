package com.nsia.officems.gop.map;

import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.nsia.officems._util.MapData;

public interface MapService {
    public ArrayNode getMap(List<MapData> mapdata);
    
}
