package com.nsia.officems.gop.map;
import java.util.List;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.nsia.officems._util.MapData;
import com.nsia.officems.gop.profile.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maps")
public class mapController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private MapService MapService;

    @GetMapping(value="/map")
    public ArrayNode getMap(){
        List<MapData> mapdata = profileService.getMapData();
        return this.MapService.getMap(mapdata);
     }

    @GetMapping(value="/map/ministries")
    public ArrayNode getMapDataByAllMinistries(){
        List<MapData> mapdata = profileService.getMapDataByAllMinistries();
        return this.MapService.getMap(mapdata);
     }
    
    @GetMapping(value="/map/authorities")
    public ArrayNode getMapDataByAllAuthorities(){
        List<MapData> mapdata = profileService.getMapDataByAllAuthorities();
        return this.MapService.getMap(mapdata);
    }

    @GetMapping(value="/map/commissions")
    public ArrayNode getMapDataByAllCommissions(){
        List<MapData> mapdata = profileService.getMapDataByAllCommissions();
        return this.MapService.getMap(mapdata);
    }

    @GetMapping(value="/map/ministryId")
    public ArrayNode getMapDataByMinstry(@RequestParam("pId") Long pId){
        List<MapData> mapdata = profileService.getMapDataByMinstry(pId);
        return this.MapService.getMap(mapdata);
    }

    @GetMapping(value="/map/authorityId")
    public ArrayNode getMapDataByAuthority(@RequestParam("pId") Long pId){
        List<MapData> mapdata = profileService.getMapDataByAuthority(pId);
        return this.MapService.getMap(mapdata);
    }

    @GetMapping(value="/map/commissionId")
    public ArrayNode getMapDataByCommission(@RequestParam("pId") Long pId){
        List<MapData> mapdata = profileService.getMapDataByCommission(pId);
        return this.MapService.getMap(mapdata);
    }


    
}
