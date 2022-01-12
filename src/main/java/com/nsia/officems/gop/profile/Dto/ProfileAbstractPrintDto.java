package com.nsia.officems.gop.profile.Dto;

import java.util.Collection;
import java.util.List;

import com.nsia.officems.gop.language.Language;
import com.nsia.officems.gop.mark_Medal.Medal;
import com.nsia.officems.gop.military_service.MilitaryService;
import com.nsia.officems.gop.panelty.Panelty;
import com.nsia.officems.gop.political_party.PoliticalParty;
import com.nsia.officems.gop.profile_education.Education;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems.gop.profile_promotion.ProfilePromotion;
import com.nsia.officems.gop.publication.Publication;
import com.nsia.officems.gop.reward.Reward;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProfileAbstractPrintDto {
    private ProfileViewDto profile;
    private List<PrintEducationDto> education;
    private String logoImageUri;
    private String profilePictureUri;
    private ProfileJob latestJob;
    private List<ProfileJobPrintDto> profileJobs;
    private List<Reward> rewards;
    private List<Panelty> penalties;
    private List<Publication> publications;
    private List<Medal> medals;
    private List<MilitaryService> militaryService;
    private Collection<Language> languages;
    private List<ProfilePromotion> promotions;
    private String dateInHijri;
    private String fontBoldUri;
    private String fontLightUri;
    private String fontRomanUri;
    private Boolean participantInPoliticalOrganizations;
    private Boolean participantInSocialOrganizations;
    private Boolean militaryMokalafiyat;
    private Boolean militaryIhteyat;
    private long praiseLetterCount;
    private long appreciationLetterCount;
    private long totalMedalCount;
    private String tarekh;
    private long totalNumberOfCashRewards;
    private long total1stAppreciationDegrees;
    private long total2ndAppreciationDegrees;
    private long total3rdAppreciationDegrees;
    private long salaryReductionCount;
    private long conversionCount;
    private long warningCount;
    private long contractTerminationCount;
    private long adviceCount;
}
