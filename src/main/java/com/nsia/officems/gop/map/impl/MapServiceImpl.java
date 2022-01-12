package com.nsia.officems.gop.map.impl;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nsia.officems._util.CustomObject;
import com.nsia.officems._util.MapData;
import com.nsia.officems.gop.map.MapService;

import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements MapService{
    public ArrayNode getMap(List<MapData> mapdata){
        ObjectNode [] nodes = new ObjectNode[34];
        ObjectMapper mapper = new ObjectMapper();
        String kabul = "کابل", bamiyan = "بامیان", ghazni = "غزنی", parwan = "پروان", balkh = "بلخ", farah = "فراه", herat = "هرات", nymroz = "نیمروز", helmand = "هـلــمنـد", 
        zabul = "زابل", kunduz = "کندز", kandahar = "کندهار", khust = "خوست", logar = "لوگر", laghman = "لغمان", paktiya = "پکتیا", kipisa = "کاپیسا", orzgan = "ارزگان", badakhshan = "بدخشان",
        noristan = "نورستان", paktika = "پکتیکا", dykundi = "دایکندی",ghor = "غور", badghez = "بادغیس", faryab = "فاریاب", juzjan = "جوزجان", saripul = "سرپل", samangan = "سمنگان", 
        baghlan = "بغلان", kuner = "کنرها", takhar = "تخار", panjsher = "پنجشیر", wardak = "میدان وردک", nangahar = "ننگرهار";

        long kabulCount = 0,bamiyanCount = 0, ghazniCount = 0, parwanCount = 0, balkhCount = 0, farahCount = 0, 
        heratCount = 0, nymrozCount = 0, helmandCount = 0, zabulCount = 0, kunduzCount = 0 , kandaharCount = 0,khustCount = 0, 
        logarCount = 0, laghmanCount = 0, paktiyaCount = 0, kipisaCount = 0, orzganCount = 0, badakhshanCount = 0,
        noristanCount = 0, paktikaCount = 0, dykundiCount = 0, ghorCount = 0, badghezCount = 0, faryabCount = 0, 
        juzjanCount = 0, saripulCount = 0, samanganCount = 0, baghlanCount = 0, kunerCount = 0, takharCount = 0, 
        panjsherCount = 0, wardakCount = 0, nangaharCount = 0;


        for(int i = 0; i < mapdata.size(); i++){
            if(mapdata.get(i).getName().equals("کابل")){
                kabul = mapdata.get(i).getName();
                kabulCount = mapdata.get(i).getCount();}

            else if(mapdata.get(i).getName().equals("بامیان"))
            {
                bamiyan = mapdata.get(i).getName();
                bamiyanCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("غزنی"))
            {
                ghazni = mapdata.get(i).getName();
                ghazniCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("پروان"))
            {
                parwan = mapdata.get(i).getName();
                parwanCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("بلخ"))
            {
                balkh = mapdata.get(i).getName();
                balkhCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("فراه"))
            {
                farah = mapdata.get(i).getName();
                farahCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("هرات"))
            {
                herat = mapdata.get(i).getName();
                heratCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("نیمروز"))
            {
                nymroz = mapdata.get(i).getName();
                nymrozCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("هـلــمنـد"))
            {
                helmand = mapdata.get(i).getName();
                helmandCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("زابل"))
            {
                zabul = mapdata.get(i).getName();
                zabulCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("کندز"))
            {
                kunduz = mapdata.get(i).getName();
                kunduzCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("کندهار"))
            {
                kandahar = mapdata.get(i).getName();
                kandaharCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("خوست"))
            {
                khust = mapdata.get(i).getName();
                khustCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("لوگر"))
            {
                logar = mapdata.get(i).getName();
                logarCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("لغمان"))
            {
                laghman = mapdata.get(i).getName();
                laghmanCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("پکتیا"))
            {
                paktiya = mapdata.get(i).getName();
                paktiyaCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("کاپیسا"))
            {
                kipisa = mapdata.get(i).getName();
                kipisaCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("ارزگان"))
            {
                orzgan = mapdata.get(i).getName();
                orzganCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("بدخشان"))
            {
                badakhshan = mapdata.get(i).getName();
                badakhshanCount = mapdata.get(i).getCount();
            }

            else if(mapdata.get(i).getName().equals("نورستان"))
            {
                noristan = mapdata.get(i).getName();
                noristanCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("پکتیکا"))
            {
                paktika = mapdata.get(i).getName();
                paktikaCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("دایکندی"))
            {
                dykundi = mapdata.get(i).getName();
                dykundiCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("غور"))
            {
                ghor = mapdata.get(i).getName();
                ghorCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("بادغیس"))
            {
                badghez = mapdata.get(i).getName();
                badghezCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("فاریاب"))
            {
                faryab = mapdata.get(i).getName();
                faryabCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("جوزجان"))
            {
                juzjan = mapdata.get(i).getName();
                juzjanCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("سرپل"))
            {
                saripul = mapdata.get(i).getName();
                saripulCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("سمنگان"))
            {
                samangan = mapdata.get(i).getName();
                samanganCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("بغلان"))
            {
                baghlan = mapdata.get(i).getName();
                baghlanCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("کنرها"))
            {
                kuner = mapdata.get(i).getName();
                kunerCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("تخار"))
            {
                takhar = mapdata.get(i).getName();
                takharCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("پنجشیر"))
            {
                panjsher = mapdata.get(i).getName();
                panjsherCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("میدان وردک"))
            {
                wardak = mapdata.get(i).getName();
                wardakCount = mapdata.get(i).getCount();
            }
            else if(mapdata.get(i).getName().equals("ننگرهار"))
            {
                nangahar = mapdata.get(i).getName();
                nangaharCount = mapdata.get(i).getCount();
            }
        }

        
        
        if(kabulCount != 0) nodes[0] = CustomObject.getObject(34.543896,  69.160652,  kabul, kabulCount);
        if(bamiyanCount != 0) nodes[1] = CustomObject.getObject(34.9073, 67.1894, bamiyan,  bamiyanCount);
        if(ghazniCount != 0) nodes[2] = CustomObject.getObject(33.4982, 67.7616, ghazni,  ghazniCount);
        if(parwanCount != 0) nodes[3] = CustomObject.getObject(34.9631, 68.8109, parwan,  parwanCount);
        if(balkhCount != 0) nodes[4] = CustomObject.getObject(36.8909, 67.1894, balkh,  balkhCount);
        if(farahCount != 0) nodes[5] = CustomObject.getObject(32.4953, 62.2627, farah,  farahCount);
        if(heratCount != 0) nodes[6] = CustomObject.getObject(34.1769, 61.7006, herat,  heratCount);
        if(nymrozCount != 0) nodes[7] = CustomObject.getObject(31.0261, 62.4504,  nymroz,  nymrozCount);
        if(helmandCount != 0)  nodes[8] = CustomObject.getObject(31.3636, 63.9586, helmand,  helmandCount);
        if(zabulCount != 0) nodes[9] = CustomObject.getObject(32.1919, 67.1894, zabul , zabulCount);
        if(kunduzCount != 0) nodes[10] = CustomObject.getObject(36.7286, 68.8679, kunduz,  kunduzCount);
        if(kandaharCount != 0) nodes[11] = CustomObject.getObject(30.9961, 65.4757, kandahar,  kandaharCount);
        if(khustCount != 0) nodes[12] = CustomObject.getObject(33.3585, 69.8597, khust, khustCount);
        if(logarCount != 0) nodes[13] = CustomObject.getObject(34.0146, 69.1924, logar,  logarCount);
        if(laghmanCount != 0)  nodes[14] = CustomObject.getObject(34.6898, 70.1456, laghman,  laghmanCount);
        if(paktiyaCount != 0) nodes[15] = CustomObject.getObject(33.7062, 69.3831, paktiya,  paktiyaCount);
        if(kipisaCount != 0) nodes[16] = CustomObject.getObject( 34.9811, 69.6215, kipisa,  kipisaCount);
        if(orzganCount != 0) nodes[17] = CustomObject.getObject( 32.9271, 66.1415, orzgan, orzganCount);
        if(badakhshanCount != 0) nodes[18] = CustomObject.getObject( 36.7348, 70.8120, badakhshan,  badakhshanCount);
        if(noristanCount != 0) nodes[19] = CustomObject.getObject( 35.3250, 70.9071, noristan,  noristanCount);
        if(paktikaCount != 0) nodes[20] = CustomObject.getObject( 32.2645, 68.5247, paktika,  paktikaCount);
        if(dykundiCount != 0) nodes[21] = CustomObject.getObject( 33.6695, 66.0464, dykundi,  dykundiCount);
        if(ghorCount != 0) nodes[22] = CustomObject.getObject( 34.0996, 64.9060, ghor,  ghorCount);
        if(badghezCount != 0) nodes[23] = CustomObject.getObject( 35.1671, 63.7695, badghez,  badghezCount);
        if(faryabCount != 0) nodes[24] = CustomObject.getObject( 36.0796, 64.9060, faryab, faryabCount);
        if(juzjanCount != 0) nodes[25] = CustomObject.getObject( 36.8970, 65.6659, juzjan,  juzjanCount);
        if(saripulCount != 0) nodes[26] = CustomObject.getObject( 36.2166, 65.9334, saripul,  saripulCount);
        if(samanganCount != 0) nodes[27] = CustomObject.getObject( 36.3156, 67.9643, samangan,  samanganCount);
        if(baghlanCount != 0) nodes[28] = CustomObject.getObject( 36.1789, 68.7453, baghlan,  baghlanCount);
        if(kunerCount != 0) nodes[29] = CustomObject.getObject( 35.0883, 71.3669, kuner,  kunerCount);
        if(takharCount != 0) nodes[30] = CustomObject.getObject( 36.6698, 69.4785, takhar,  takharCount);
        if(panjsherCount != 0) nodes[31] = CustomObject.getObject( 35.5026, 69.9550, panjsher,  panjsherCount);
        if(wardakCount != 0) nodes[32] = CustomObject.getObject( 34.3513, 68.2385, wardak,  wardakCount);
        if(nangaharCount != 0) nodes[33] = CustomObject.getObject( 34.1718, 70.6217,nangahar,  nangaharCount);

        ArrayNode summary = mapper.createArrayNode();
        summary.addAll(Arrays.asList(nodes));
        return summary;
    }
}
