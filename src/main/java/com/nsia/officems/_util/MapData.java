package com.nsia.officems._util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class MapData {
    private String name;
    private Long count;

    public MapData(String name, Long count){
        this.name = name;
        this.count = count;
    } 
}
