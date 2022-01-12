package com.nsia.officems.gop.profile.Dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._admin.authority.AuthorityService;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems._admin.country.Country;
import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems._admin.district.District;
import com.nsia.officems._admin.district.DistrictService;
import com.nsia.officems.gop.employeeGrade.EmployeeGradeService;
import com.nsia.officems.gop.employeePosition.EmployeePositionService;
import com.nsia.officems.gop.employeeStatus.EmployeeStatusService;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGradeService;
import com.nsia.officems.gop.ethnic.EthnicService;
import com.nsia.officems.gop.gender.GenderService;
import com.nsia.officems.gop.language.Language;
import com.nsia.officems.gop.language.LanguageService;
import com.nsia.officems._admin.ministry.MinistryService;
import com.nsia.officems.gop.national_language.NationalLanguage;
import com.nsia.officems.gop.national_language.NationalLanguageService;
import com.nsia.officems.gop.nationality.NationalityService;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems._admin.province.Province;
import com.nsia.officems._admin.province.ProvinceService;
import com.nsia.officems.gop.religion.ReligionService;
import com.nsia.officems.gop.sect.SectService;

import org.hibernate.property.access.spi.GetterFieldImpl;

public class ProfileDtoMapper {
    public static Profile MapProfileDto(Profile profile, ProfileDto dto, CountryService countryService, ProvinceService provinceService,
            DistrictService districtService, EthnicService ethnicService, NationalityService nationalityService,
            ReligionService religionService, SectService sectService, LanguageService languageService,
            EmployeeGradeService gradeService, EmployeeStatusService statusService, MinistryService ministryService,
            AuthorityService authorityService, EmployeePositionService positionService, 
            CommissionService commissionService, GenderService genderService, 
            NationalLanguageService nationalLanguageService, EmployeeMilitaryGradeService employeeMilitaryGradeService){ 
                DateTimeChange changeDate = new DateTimeChange();
        

        try {
            profile.setActive(true);
            profile.setDisable((dto.getDisable() == null || dto.getDisable() == false)? false: true);
            profile.setFirstName(dto.getFirstName());
            profile.setLastName(dto.getLastName());
            profile.setFatherName(dto.getFatherName());
            profile.setGrandFatherName(dto.getGrandFatherName());
            profile.setEthnic(dto.getEthnic() == null ? null : ethnicService.findById(dto.getEthnic()));
            profile.setNationality(
                    dto.getNationality() == null ? null : nationalityService.findById(dto.getNationality()));
            profile.setSect(dto.getSect() == null ? null : sectService.findById(dto.getSect()));
            profile.setBlood(dto.getBlood());
            profile.setDob(dto.getDob());
            profile.setReligion(dto.getReligion() == null ? null : religionService.findById(dto.getReligion()));
            profile.setDobGregorian(dto.getDobGregorian()==null? null: changeDate.convertStringToDate(dto.getDobGregorian()));
            profile.setBirthCountry(
                    dto.getBirthCountry() == null ? null : countryService.findById(dto.getBirthCountry()));
            profile.setBirthProvince(
                    dto.getBirthProvince() == null ? null : provinceService.findById(dto.getBirthProvince()));
            profile.setBirthDistrict(
                    dto.getBirthDistrict() == null ? null : districtService.findById(dto.getBirthDistrict()));
            profile.setBirthVillage(dto.getBirthVillage());
            profile.setOriginalCountry(
                    dto.getOriginalCountry() == null ? null : countryService.findById(dto.getOriginalCountry()));
            profile.setOriginalProvince(
                    dto.getOriginalProvince() == null ? null : provinceService.findById(dto.getOriginalProvince()));
            profile.setOriginalDistrict(
                    dto.getOriginalDistrict() == null ? null : districtService.findById(dto.getOriginalDistrict()));
            profile.setOriginalVillage(dto.getOriginalVillage());
            profile.setCurrentCountry(
                    dto.getCurrentCountry() == null ? null : countryService.findById(dto.getCurrentCountry()));
            profile.setCurrentProvince(
                    dto.getCurrentProvince() == null ? null : provinceService.findById(dto.getCurrentProvince()));
            profile.setCurrentDistrict(
                    dto.getCurrentDistrict() == null ? null : districtService.findById(dto.getCurrentDistrict()));
            profile.setCurrentVillage(dto.getCurrentVillage());
            profile.setTazkiraNumber(dto.getTazkiraNumber());
            profile.setTazkiraTog(dto.getTazkiraTog());
            profile.setTazkiraRegister(dto.getTazkiraRegister());
            profile.setAppointType(dto.getAppointType());
            profile.setTazkiraPage(dto.getTazkiraPage());
            profile.setTazkiraDate(dto.getTazkiraDate()==null? null: changeDate.convertPersianDateToGregorianDate(dto.getTazkiraDate()));
            profile.setTazkiraPlace(dto.getTazkiraPlace());
            profile.setEnid(dto.getEnid());
            profile.setLanguage(dto.getLanguage() == null ? null : languageService.findbyIdIn(dto.getLanguage()));
            profile.setNationalLanguages(dto.getNationalLanguages() == null? null: nationalLanguageService.findbyIdIn(dto.getNationalLanguages()));
            profile.setPhone(dto.getPhone());
            profile.setYear(dto.getYear()==null? null: changeDate.convertPersianDateToGregorianDate(dto.getYear()));
            profile.setEmail(dto.getEmail());
            profile.setAppointDate(dto.getAppointDate()==null? null: changeDate.convertPersianDateToGregorianDate(dto.getAppointDate()));
            profile.setDecreeNumber(dto.getDecreeNumber());
            profile.setPositionTitle(dto.getPositionTitle());
            profile.setPosition(dto.getPosition() == null? null : positionService.findById(dto.getPosition()));
            profile.setGrade(dto.getGrade() == null ? null : gradeService.findById(dto.getGrade()));
            profile.setStatus(dto.getStatus() == null ? null : statusService.findById(dto.getStatus()));
            profile.setQadamYear(dto.getQadamYear());
            profile.setQadamMonth(dto.getQadamMonth());
            profile.setQadamDay(dto.getQadamDay());
            profile.setMinistry(dto.getMinistry() == null ? null : ministryService.findById(dto.getMinistry()));
            profile.setAuthority(dto.getAuthority() == null ? null : authorityService.findById(dto.getAuthority()));
            profile.setCommission(dto.getCommission() == null? null : commissionService.findById(dto.getCommission()));
            profile.setSuggestionNumber(dto.getSuggestionNumber());
            profile.setSuggestionDate(dto.getSuggestionDate()== null? null: changeDate.convertPersianDateToGregorianDate(dto.getSuggestionDate()));
            profile.setGender(dto.getGender() == null? null: genderService.findById(dto.getGender()));
            profile.setMilitaryGrade(dto.getMilitaryGrade() == null? null: employeeMilitaryGradeService.findById(dto.getMilitaryGrade()));
            profile.setMilitaryPosition(dto.getMilitaryPosition() == null? null: employeeMilitaryGradeService.findById(dto.getMilitaryPosition()));
            return profile;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping profile");
            return null;
        }

    }

    public static ProfileViewDto ProfileMapViewDto(Profile profile, LanguageService languageService) {
        ProfileViewDto p = new ProfileViewDto();
        try {
            p.setId(profile.getId());
            p.setProfileCode(profile.getProfileCode());
            p.setFirstName(profile.getFirstName());
            p.setLastName(profile.getLastName());
            p.setFatherName(profile.getFatherName());
            p.setGrandFatherName(profile.getGrandFatherName());
            p.setEthnic(profile.getEthnic() == null ? null : profile.getEthnic().getNameDr());
            p.setNationality(profile.getNationality().equals(null) ? null : profile.getNationality().getNameDr());
            p.setReligion(profile.getReligion() == null ? null : profile.getReligion().getNameDr());
            p.setSect(profile.getSect() == null ? null : profile.getSect().getNameDr());
            p.setBlood(profile.getBlood());
            p.setDob(profile.getDob());
            p.setDobGregorian(profile.getDobGregorian().toString());
            p.setBirthCountry(profile.getBirthCountry() == null ? null : profile.getBirthCountry().getNameDr());
            p.setBirthProvince(profile.getBirthProvince() == null ? null : profile.getBirthProvince().getNameDr());
            p.setBirthDistrict(profile.getBirthDistrict() == null ? null : profile.getBirthDistrict().getNameDr());
            p.setBirthVillage(profile.getBirthVillage());
            p.setOriginalCountry(profile.getOriginalCountry() == null ? null : profile.getOriginalCountry().getNameDr());
            p.setOriginalProvince(
                    profile.getOriginalProvince() == null ? null : profile.getOriginalProvince().getNameDr());
            p.setOriginalDistrict(
                    profile.getOriginalDistrict() == null ? null : profile.getOriginalDistrict().getNameDr());
            p.setOriginalVillage(profile.getOriginalVillage());
            p.setCurrentCountry(profile.getOriginalCountry() == null ? null : profile.getCurrentCountry().getNameDr());
            p.setCurrentProvince(profile.getCurrentProvince() == null ? null : profile.getCurrentProvince().getNameDr());
            p.setCurrentDistrict(profile.getCurrentDistrict() == null ? null : profile.getCurrentDistrict().getNameDr());
            p.setCurrentVillage(profile.getCurrentVillage());
            p.setTazkiraNumber(profile.getTazkiraNumber());
            p.setTazkiraTog(profile.getTazkiraTog());
            p.setTazkiraRegister(profile.getTazkiraRegister());
            p.setTazkiraDate(profile.getTazkiraDate()== null? null: profile.getTazkiraDate().toString());
            p.setTazkiraPlace(profile.getTazkiraPlace());
            p.setTazkiraPage(profile.getTazkiraPage());
            p.setEnid(profile.getEnid());
            p.setPhone(profile.getPhone());
            p.setEmail(profile.getEmail());
            p.setYear(profile.getYear().toString());
            p.setAppointDate(profile.getAppointDate( )== null? null: profile.getAppointDate().toString());
            p.setAppointType(profile.getAppointType());
            p.setDecreeNumber(profile.getDecreeNumber());
            p.setPositionTitle(profile.getPositionTitle());
            p.setPosition(profile.getPosition() == null? null: profile.getPosition().getNameDr());
            p.setGrade(profile.getGrade() == null ? null : profile.getGrade().getNameDr());
            p.setStatus(profile.getStatus() == null ? null : profile.getStatus().getNameDr());
            p.setQadamYear(profile.getQadamYear());
            p.setQadamMonth(profile.getQadamMonth());
            p.setQadamDay(profile.getQadamDay());
            p.setMinistry(profile.getMinistry() == null ? null : profile.getMinistry().getNameDr());
            p.setAuthority(profile.getAuthority() == null ? null : profile.getAuthority().getNameDr());
            p.setCommission(profile.getCommission() == null? null : profile.getCommission().getNameDr());
            p.setSuggestionDate(profile.getSuggestionDate() == null? null: profile.getSuggestionDate().toString());
            p.setSuggestionNumber(profile.getSuggestionNumber());
            p.setGender(profile.getGender() == null? null: profile.getGender().getNameDr());
            p.setMilitaryGrade(profile.getMilitaryGrade() == null? null: profile.getMilitaryGrade().getNameDr());
            p.setMilitaryPosition(profile.getMilitaryPosition() == null? null: profile.getMilitaryPosition().getNameDr());
            p.setApprove(profile.getApprove() == null? null: profile.getApprove());
            
            if(profile.getLanguage() !=null)
            {
                Collection<Language> languages = profile.getLanguage();
                List<String> languageNames = new ArrayList<>();
                for(Language i: languages)
                {
                        languageNames.add(i.getNameDr());
                }
                p.setLanguage(languageNames);
            }
            else{
                    p.setLanguage(null);
            }

            if(profile.getNationalLanguages() !=null)
            {
                Collection<NationalLanguage> languages = profile.getNationalLanguages();
                List<String> languageNames = new ArrayList<>();
                for(NationalLanguage i: languages)
                {
                        languageNames.add(i.getNameDr());
                }
                p.setNationalLanguages(languageNames);
            }
            else{
                    p.setNationalLanguages(null);
            }

            return p;

        } catch (Exception e) {
            System.out.println("Excaption in mapp view class");
        }

        return null;
    }
    
    public static ProfileDto ProfileMapEditDto(Profile profile, LanguageService languageService) {
        ProfileDto p = new ProfileDto();
        try {
            p.setId(profile.getId());
            p.setDisable(profile.getDisable());
            p.setProfileCode(profile.getProfileCode());
            p.setFirstName(profile.getFirstName());
            p.setLastName(profile.getLastName());
            p.setFatherName(profile.getFatherName());
            p.setGrandFatherName(profile.getGrandFatherName());
            p.setEthnic(profile.getEthnic() == null ? null : profile.getEthnic().getId());
            p.setNationality(profile.getNationality().equals(null) ? null : profile.getNationality().getId());
            p.setReligion(profile.getReligion() == null ? null : profile.getReligion().getId());
            p.setSect(profile.getSect() == null ? null : profile.getSect().getId());
            p.setBlood(profile.getBlood());
            p.setDob(profile.getDob());
            p.setDobGregorian(profile.getDobGregorian() == null? null: profile.getDobGregorian().toString());
            p.setBirthCountry(profile.getBirthCountry() == null ? null : profile.getBirthCountry().getId());
            p.setBirthProvince(profile.getBirthProvince() == null ? null : profile.getBirthProvince().getId());
            p.setBirthDistrict(profile.getBirthDistrict() == null ? null : profile.getBirthDistrict().getId());
            p.setBirthVillage(profile.getBirthVillage());
            p.setOriginalCountry(profile.getOriginalCountry() == null ? null : profile.getOriginalCountry().getId());
            p.setOriginalProvince(
                    profile.getOriginalProvince() == null ? null : profile.getOriginalProvince().getId());
            p.setOriginalDistrict(
                    profile.getOriginalDistrict() == null ? null : profile.getOriginalDistrict().getId());
            p.setOriginalVillage(profile.getOriginalVillage());
            p.setCurrentCountry(profile.getOriginalCountry() == null ? null : profile.getCurrentCountry().getId());
            p.setCurrentProvince(profile.getCurrentProvince() == null ? null : profile.getCurrentProvince().getId());
            p.setCurrentDistrict(profile.getCurrentDistrict() == null ? null : profile.getCurrentDistrict().getId());
            p.setCurrentVillage(profile.getCurrentVillage());
            p.setTazkiraNumber(profile.getTazkiraNumber());
            p.setAppointType(profile.getAppointType());
            p.setTazkiraTog(profile.getTazkiraTog());
            p.setTazkiraRegister(profile.getTazkiraRegister());
            p.setTazkiraDate(profile.getTazkiraDate() == null? null: profile.getTazkiraDate().toString());
            p.setTazkiraPlace(profile.getTazkiraPlace());
            p.setTazkiraPage(profile.getTazkiraPage());
            p.setEnid(profile.getEnid());
            p.setPhone(profile.getPhone());
            p.setEmail(profile.getEmail());
            p.setYear(profile.getYear().toString());
            p.setAppointDate(profile.getAppointDate()== null? null: profile.getAppointDate().toString());
            p.setDecreeNumber(profile.getDecreeNumber());
            p.setPositionTitle(profile.getPositionTitle());
            p.setPosition(profile.getPosition() == null? null: profile.getPosition().getId());
            p.setGrade(profile.getGrade() == null ? null : profile.getGrade().getId());
            p.setStatus(profile.getStatus() == null ? null : profile.getStatus().getId());
            p.setQadamYear(profile.getQadamYear());
            p.setQadamMonth(profile.getQadamMonth());
            p.setQadamDay(profile.getQadamDay());
            p.setMinistry(profile.getMinistry() == null ? null : profile.getMinistry().getId());
            p.setAuthority(profile.getAuthority() == null ? null : profile.getAuthority().getId());
            p.setCommission(profile.getCommission() == null? null : profile.getCommission().getId());
            p.setMilitaryGrade(profile.getMilitaryGrade() == null? null: profile.getMilitaryGrade().getId());
            p.setMilitaryPosition(profile.getMilitaryPosition() == null? null: profile.getMilitaryPosition().getId());
            p.setSuggestionDate(profile.getSuggestionDate()== null? null: profile.getSuggestionDate().toString());
            p.setSuggestionNumber(profile.getSuggestionNumber());
            p.setGender(profile.getGender() == null? null: profile.getGender().getId());
            
            if(profile.getLanguage() !=null)
            {
                Collection<Language> languages = profile.getLanguage();
                List<Long> languageNames = new ArrayList<>();
                for(Language i: languages)
                {
                        languageNames.add(i.getId());
                }
                p.setLanguage(languageNames);
            }

            else {
                    p.setLanguage(null);
            }

            if(profile.getNationalLanguages() != null)
            {
                Collection<NationalLanguage> languages = profile.getNationalLanguages();
                List<Long> languageNames = new ArrayList<>();
                for(NationalLanguage i: languages)
                {
                        languageNames.add(i.getId());
                }
                p.setNationalLanguages(languageNames);
            }
            else{
                    p.setNationalLanguages(null);
            }

            

            return p;

        } catch (Exception e) {
            System.out.println("Excaption in mappe class");
        }

        return null;
    }

}
