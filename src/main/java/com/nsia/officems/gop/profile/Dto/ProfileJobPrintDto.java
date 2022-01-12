package com.nsia.officems.gop.profile.Dto;

import java.util.Date;

import com.nsia.officems.gop.accountability.Accountability;
import com.nsia.officems._admin.authority.Authority;
import com.nsia.officems._admin.commission.Commission;
import com.nsia.officems.gop.employeeGrade.EmployeeGrade;
import com.nsia.officems.gop.employeePosition.EmployeePosition;
import com.nsia.officems.gop.employeeStatus.EmployeeStatus;
import com.nsia.officems._admin.ministry.Ministry;
import com.nsia.officems.gop.panelty.Panelty;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.transfer_profile.Transfer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileJobPrintDto {

    private Long id;

    private String positionTitle;

    private String maktubNumber;

    private Date maktubDate;

    private String maktubDateString;

    private EmployeeGrade grade;

    private EmployeePosition position;

    private Ministry ministry;

    private Authority authority;

    private Commission commission;

    private Profile profile;

    private Accountability accountability;

    private Transfer transfer;

    private Panelty panelty;

    private Boolean intialJob;

    private Date endDate;

    private String endDateString;

    private Boolean isReward;
    private Boolean isPanelty;

    private Boolean jobBreak;

    private String breakDetail;

    private EmployeeStatus status;
}
