package com.nsia.officems.gop.profile;

import java.util.List;

import com.nsia.officems.gop.mark_Medal.Medal;
import com.nsia.officems.gop.reward.Reward;
import com.nsia.officems.gop.panelty.Panelty;
import com.nsia.officems.gop.profile.Dto.PrintEducationDto;
import com.nsia.officems.gop.profile_education.Education;

public interface ProfilePrintService {

    public long totalNumberOfCashRewards(List<Reward> rewards);

    public long totalPraiseLetters(List<Reward> rewards);

    public long totalAppreciationDegrees(List<Reward> rewards);

    public long totalMedals(List<Medal> medals);

    public long total1stAppreciationDegrees(List<Reward> rewards);

    public long total2ndAppreciationDegrees(List<Reward> rewards);

    public long total3rdAppreciationDegrees(List<Reward> rewards);

    public long salaryReductionCount(List<Panelty> penalties);

    public long conversionCount(List<Panelty> penalties);

    public long warningCount(List<Panelty> penalties);

    public long contractTerminationCount(List<Panelty> penalties);

    public long adviceCount(List<Panelty> penalties);

    public List<PrintEducationDto> mapEducation(List<Education> educations);

}
