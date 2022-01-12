package com.nsia.officems.gop.Profile_checklist;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChecklistTitle {
    
    private String primaryInfo = "Info";
    private String education = "education";
    private String reward = "reward";
    private String panelty = "panelty";
    private String publication = "publication";
    private String Hononary_service = "hononary";
    private String medal = "medal";
    private String family_members = "family";
    private String medical = "medical";
    private String military = "military";
    private String travel = "travel";
    private String transfer = "transfer";
    private String promotion = "promotion";
    private String salary = "salary";
    private String accountability = "accountability";
    private String off_duty_crime = "crime";
    private String fired_from_duty = "fired";
    private String photo = "photo";
    private String document = "document";
    private String academic_degree = "academic";
    private String training = "training";
    private String political_party = "party";
    private String resignation = "resignation";

    public List<String> gettitles(){
        List<String> title = new ArrayList<>();
        title.add(this.getPrimaryInfo());
        title.add(this.getEducation());
        title.add(this.getReward());
        title.add(this.getPanelty());
        title.add(this.getHononary_service());
        title.add(this.getMedal());
        title.add(this.getFamily_members());
        title.add(this.getMedical());
        title.add(this.getMilitary());
        title.add(this.getTravel());
        title.add(this.getTransfer());
        title.add(this.getPromotion());
        title.add(this.getSalary());
        title.add(this.getAccountability());
        title.add(this.getOff_duty_crime());
        title.add(this.getFired_from_duty());
        title.add(this.getPhoto());
        title.add(this.getDocument());
        title.add(this.getPublication());
        title.add(this.getAcademic_degree());
        title.add(this.getTraining());
        title.add(this.getPolitical_party());
        title.add(this.getResignation());

        return title;
    }
    
}
