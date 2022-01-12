package com.nsia.officems.gop.profile.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.gop.mark_Medal.Medal;
import com.nsia.officems.gop.panelty.Panelty;
import com.nsia.officems.gop.paneltyType.PaneltyType;
import com.nsia.officems.gop.profile.ProfilePrintService;
import com.nsia.officems.gop.profile.Dto.PrintEducationDto;
import com.nsia.officems.gop.profile_education.Education;
import com.nsia.officems.gop.profile_education.EducationService;
import com.nsia.officems.gop.reward.Reward;
import com.nsia.officems.gop.rewardType.RewardType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProfilePrintServiceImplementation implements ProfilePrintService {

    private final DateTimeChange _dateTimeChange;
    private final EducationService _educationService;
    private final ModelMapper _modelMapper;

    @Override
    public long totalNumberOfCashRewards(List<Reward> rewards) {
        return rewards.stream().filter(reward -> reward.getCashAmount() != null).count();
    }

    @Override
    public long totalPraiseLetters(List<Reward> rewards) {
        List<RewardType> rewardType = rewards.stream().map(Reward::getType).collect(Collectors.toList());
        return rewardType.stream().filter(type -> type.getNameDr().equals("تحسین نامه")).count();
    }

    @Override
    public long totalAppreciationDegrees(List<Reward> rewards) {
        List<RewardType> rewardType = rewards.stream().map(Reward::getType).collect(Collectors.toList());
        return rewardType.stream().filter(type -> type.getNameDr().equals("تحسین نامه")).count();
    }

    @Override
    public long totalMedals(List<Medal> medals) {
        return medals.stream().count();
    }

    @Override
    public long total1stAppreciationDegrees(List<Reward> rewards) {
        return rewards.stream().filter(reward -> reward.getAppreciationDegree() != null)
                .filter(reward -> reward.getAppreciationDegree().equals("1")).count();
    }

    @Override
    public long total2ndAppreciationDegrees(List<Reward> rewards) {
        return rewards.stream().filter(reward -> reward.getAppreciationDegree() != null)
                .filter(reward -> reward.getAppreciationDegree().equals("2")).count();

    }

    @Override
    public long total3rdAppreciationDegrees(List<Reward> rewards) {
        return rewards.stream().filter(reward -> reward.getAppreciationDegree() != null)
                .filter(reward -> reward.getAppreciationDegree().equals("3")).count();
    }

    @Override
    public long salaryReductionCount(List<Panelty> penalties) {
        List<PaneltyType> penaltyType = penalties.stream().map(Panelty::getType).collect(Collectors.toList());
        return penaltyType.stream().filter(type -> type.getNameDr().equals(" کسر معاش")).count();
    }

    @Override
    public long conversionCount(List<Panelty> penalties) {
        List<PaneltyType> penaltyType = penalties.stream().map(Panelty::getType).collect(Collectors.toList());
        return penaltyType.stream().filter(type -> type.getNameDr().equals("تبدیلی")).count();
    }

    @Override
    public long warningCount(List<Panelty> penalties) {
        List<PaneltyType> penaltyType = penalties.stream().map(Panelty::getType).collect(Collectors.toList());
        return penaltyType.stream().filter(type -> type.getNameDr().equals("اخطار")).count();
    }

    @Override
    public long contractTerminationCount(List<Panelty> penalties) {
        List<PaneltyType> penaltyType = penalties.stream().map(Panelty::getType).collect(Collectors.toList());
        return penaltyType.stream().filter(type -> type.getNameDr().equals("فسخ قرارداد کار")).count();
    }

    @Override
    public long adviceCount(List<Panelty> penalties) {
        List<PaneltyType> penaltyType = penalties.stream().map(Panelty::getType).collect(Collectors.toList());
        return penaltyType.stream().filter(type -> type.getNameDr().equals("توصیه")).count();
    }

    @Override
    public List<PrintEducationDto> mapEducation(List<Education> educations) {
        List<PrintEducationDto> newEducations = educations.stream()
                .map(education -> _modelMapper.map(education, PrintEducationDto.class)).collect(Collectors.toList());
        return newEducations;
    }

}
