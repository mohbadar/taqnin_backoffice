package com.nsia.officems.gop.profile_job.dto;
import com.nsia.officems.gop.accountability.Accountability;
import com.nsia.officems.gop.panelty.Panelty;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems.gop.transfer_profile.Transfer;

public class JobMapper {
    public static ProfileJob MapProfileObj(ProfileJob Job, Profile profile){ 

        try{
            Job.setActive(true);
            Job.setIntialJob(true);
            Job.setMaktubNumber(profile.getDecreeNumber() == null? null: profile.getDecreeNumber());
            Job.setMaktubDate(profile.getAppointDate() == null? null: profile.getAppointDate());
            Job.setPositionTitle(profile.getPositionTitle() == null? null : profile.getPositionTitle());
            Job.setGrade(profile.getGrade() == null? null : profile.getGrade());
            Job.setMilitaryGrade(profile.getMilitaryGrade() == null? null: profile.getMilitaryGrade());
            Job.setPosition(profile.getPosition() == null? null : profile.getPosition());
            Job.setMilitaryPosition(profile.getMilitaryPosition() == null? null: profile.getMilitaryPosition());
            Job.setMinistry(profile.getMinistry() == null? null : profile.getMinistry());
            Job.setAuthority(profile.getAuthority() == null? null: profile.getAuthority());
            Job.setCommission(profile.getCommission() == null? null: profile.getCommission());
            Job.setProfile(profile.getId() == null? null: profile);
            Job.setTransfer(null);
            Job.setAccountability(null);
            Job.setPanelty(null);
            return Job;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }

    public static ProfileJob MapTransferObj(ProfileJob Job, Transfer transfer, Profile profile){ 

        try{
            Job.setActive(true);
            Job.setIntialJob(false);
            Job.setMaktubNumber(transfer.getMaktubNumber() == null? null: transfer.getMaktubNumber());
            Job.setMaktubDate(transfer.getMaktubDate() == null? null: transfer.getMaktubDate());
            Job.setPositionTitle(transfer.getPositionTitle() == null? null : transfer.getPositionTitle());
            Job.setGrade(profile.getGrade() == null? null : profile.getGrade());
            Job.setPosition(transfer.getPosition() == null? null : transfer.getPosition());
            Job.setMilitaryPosition(transfer.getMilitaryPosition() == null? null: transfer.getMilitaryPosition());
            Job.setMinistry(transfer.getMinistry() == null? null : transfer.getMinistry());
            Job.setAuthority(transfer.getAuthority() == null? null: transfer.getAuthority());
            Job.setCommission(transfer.getCommission() == null? null: transfer.getCommission());
            Job.setProfile(transfer.getProfile() == null? null: transfer.getProfile());
            Job.setTransfer(transfer);
            Job.setAccountability(null);
            Job.setPanelty(null);
            return Job;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
    

    public static ProfileJob MapPaneltyObj(ProfileJob Job, Panelty panelty, Profile profile){ 

        try{
            Job.setActive(true);
            Job.setIntialJob(false);
            Job.setMaktubNumber(panelty.getDecreeNumber() == null? null: panelty.getDecreeNumber());
            Job.setMaktubDate(panelty.getDecreeDate() == null? null: panelty.getDecreeDate());
            Job.setPositionTitle(panelty.getPositionTitle() == null? null : panelty.getPositionTitle());
            Job.setGrade(null);
            Job.setPosition(panelty.getPosition() == null? null : panelty.getPosition());
            Job.setMilitaryPosition(panelty.getMilitaryPosition() == null? null: panelty.getMilitaryPosition());
            Job.setMinistry(panelty.getMinistry() == null? null : panelty.getMinistry());
            Job.setAuthority(panelty.getAuthority() == null? null: panelty.getAuthority());
            Job.setCommission(panelty.getCommission() == null? null: panelty.getCommission());
            Job.setProfile(profile == null? null: profile);
            Job.setTransfer(null);
            Job.setAccountability(null);
            Job.setPanelty(panelty == null? null: panelty);
            return Job;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }

    public static ProfileJob MapAccountiblityObj(ProfileJob Job, Accountability accountability, Profile profile){ 

        try{
            Job.setActive(true);
            Job.setIntialJob(false);
            Job.setMaktubNumber(null);
            Job.setMaktubDate(accountability.getStartDate() == null? null: accountability.getStartDate());
            Job.setPositionTitle(accountability.getPositionTitle() == null? null : accountability.getPositionTitle());
            Job.setEndDate(accountability.getEndDate() == null? null: accountability.getEndDate());
            Job.setGrade(null);
            Job.setPosition(accountability.getPosition() == null? null : accountability.getPosition());
            Job.setMilitaryGrade(accountability.getMilitaryPosition() == null? null: accountability.getMilitaryPosition());
            Job.setMinistry(accountability.getMinistry() == null? null : accountability.getMinistry());
            Job.setAuthority(accountability.getAuthority() == null? null: accountability.getAuthority());
            Job.setCommission(accountability.getCommission() == null? null: accountability.getCommission());
            Job.setProfile(profile == null? null: profile);
            Job.setAccountability(accountability);
            Job.setTransfer(null);
            Job.setPanelty(null);
            return Job;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
