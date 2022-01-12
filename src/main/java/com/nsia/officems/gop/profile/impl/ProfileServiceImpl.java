package com.nsia.officems.gop.profile.impl;

import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile.Dto.ProfileDto;
import com.nsia.officems.gop.profile.Dto.ProfileDtoMapper;
import com.nsia.officems.gop.profile.Dto.ProfileViewDto;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.gop.profile_fired.ProfileFired;
import com.nsia.officems.gop.profile_job.ProfileJobService;
import com.nsia.officems.gop.profile_promotion.ProfilePromotion;
import com.nsia.officems._admin.province.Province;
import com.nsia.officems._admin.province.ProvinceService;
import com.nsia.officems.gop.religion.ReligionService;
import com.nsia.officems.gop.sect.SectService;
import com.nsia.officems.gop.transfer_profile.Transfer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserAuthService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._util.MapData;
import com.nsia.officems._admin.authority.AuthorityService;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems._admin.country.Country;
import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems._admin.district.District;
import com.nsia.officems._admin.district.DistrictService;
import com.nsia.officems.gop.employeeGrade.EmployeeGrade;
import com.nsia.officems.gop.employeeGrade.EmployeeGradeService;
import com.nsia.officems.gop.employeePosition.EmployeePositionService;
import com.nsia.officems.gop.employeeStatus.EmployeeStatusService;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGradeService;
import com.nsia.officems.gop.ethnic.Ethnic;
import com.nsia.officems.gop.ethnic.EthnicService;
import com.nsia.officems.gop.gender.GenderService;
import com.nsia.officems.gop.language.LanguageService;
import com.nsia.officems._admin.ministry.MinistryService;
import com.nsia.officems.gop.national_language.NationalLanguageService;
import com.nsia.officems.gop.nationality.NationalityService;
import com.nsia.officems.gop.panelty.Panelty;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    DateTimeChange changeDate = new DateTimeChange();

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    UserService userService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private EthnicService ethnicService;

    @Autowired
    private NationalityService nationalityService;

    @Autowired
    private ReligionService religionService;

    @Autowired
    private SectService sectService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private EmployeeGradeService gradeService;

    @Autowired
    private EmployeeStatusService statusService;

    @Autowired
    private MinistryService ministryService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private ProfileJobService profileJobService;

    @Autowired
    private EmployeePositionService positionService;

    @Autowired
    private CommissionService commissionService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    NationalLanguageService nationalLanguageService;

    @Autowired
    private EmployeeMilitaryGradeService employeeMilitaryGradeService;

    @Autowired
    private GenderService genderService;

    public long count(){
        return this.profileRepository.count();
    }

    public long countActive(){
        return this.profileRepository.countActive();
    }

    public List getProfileCountByEthnic(){
        return this.profileRepository.getProfileCountByEthnic();
    }

    public List getProfileCountBySect(){
        return this.profileRepository.getProfileCountBySect();
    }

    public List getProfileCountByGender(){
        return this.profileRepository.getProfileCountByGender();
    }

    public List getProfileCountEducation(){
        return this.profileRepository.getProfileEducation();
    }

    public List getProfileCountMinistry(){
        return this.profileRepository.getProfileCountByMinistry();
    }

    public List getProfileCountAuthority(){
        return this.profileRepository.getProfileCountByAuthority();
    }
    public List getProfileCountCommission(){
        return this.profileRepository.getProfileCountByCommission();
    }

    public List getProfileCountAge(){
        return this.profileRepository.getProfileCountByAge();
    }

    public List<MapData> getMapData(){
        return this.profileRepository.getMapData();
    }
    public List getEthnicByMinistry(Long id){
        return this.profileRepository.getEthnicByMinistry(id);
    }
    public List getEthnicByAuthority(Long id){
        return this.profileRepository.getEthnicByAuthority(id);
    }
    public List getEthnicByCommission(Long id){
        return this.profileRepository.getEthnicByCommission(id);
    }
    public List getEthnicByAllMinistries(){
        return this.profileRepository.getEthnicByAllMinistries();
    }
    public List getEthnicByAllAuthorities(){
        return this.profileRepository.getEthnicByAllAuthorities();
    }
    public List getEthnicByAllCommissions(){
        return this.profileRepository.getEthnicByAllCommissions();
    }

    public List getSectByAllMinistries(){
        return this.profileRepository.getSectByAllMinistries();
    }
    public List getSectByAllAuthories(){
        return this.profileRepository.getSectByAllAuthories();
    }
    public List getSectByAllCommission(){
        return this.profileRepository.getSectByAllCommission();
    }

    public List getSectByMinistry(Long id){
        return this.profileRepository.getSectByMinistry(id);
    }
    public List getSectByAuthority(Long id){
        return this.profileRepository.getSectByAuthority(id);
    }
    public List getSectByCommission(Long id){
        return this.profileRepository.getSectByCommission(id);
    }
    public List getGenderByAllMinistries(){
        return this.profileRepository.getGenderByAllMinistries();
    }
    public List getGenderByAllAuthorities(){
        return this.profileRepository.getGenderByAllAuthorities();
    }
    public List getGenderByAllCommission(){
        return this.profileRepository.getGenderByAllCommission();
    }
    public List getGenderByMinistry(Long id){
        return this.profileRepository.getGenderByMinistry(id);
    }
    public List getGenderByAuthority(Long id){
        return this.profileRepository.getGenderByAuthority(id);
    }
    public List getGenderByCommission(Long id){
        return this.profileRepository.getGenderByCommission(id);
    }

    public List getAgeByAllMinistries(){
        return this.profileRepository.getAgeByAllMinistries();
    }
    public List getAgeByAllAuthorities(){
        return this.profileRepository.getAgeByAllAuthorities();
    }
    public List getAgeByAllCommissions(){
        return this.profileRepository.getAgeByAllCommissions();
    }

    public List getAgeByMinistry(Long id){
        return this.profileRepository.getAgeByMinistry(id);
    }
    public List getAgeByAuthority(Long id){
        return this.profileRepository.getAgeByAuthority(id);
    }
    public List getAgeByCommission(Long id){
        return this.profileRepository.getAgeByCommission(id);
    }

    public List getEducationByAllMinistries(){
        return this.profileRepository.getEducationByAllMinistries();
    }
    public List getEducationByAllAuthorities(){
        return this.profileRepository.getEducationByAllAuthorities();
    }
    public List getEducationByAllCommissions(){
        return this.profileRepository.getEducationByAllCommissions();
    }
    public List getEducationByMinistry(Long id){
        return this.profileRepository.getEducationByMinistry(id);
    }
    public List getEducationByAuthority(Long id){
        return this.profileRepository.getEducationByAuthority(id);
    }
    public List getEducationByCommission(Long id){
        return this.profileRepository.getEducationByCommission(id);
    }
    public List<MapData> getMapDataByAllMinistries(){
        return this.profileRepository.getMapDataByAllMinistries();
    }
    public List<MapData> getMapDataByAllAuthorities(){
        return this.profileRepository.getMapDataByAllAuthorities();
    }
    public List<MapData> getMapDataByAllCommissions(){
        return this.profileRepository.getMapDataByAllCommissions();
    }
    public List<MapData> getMapDataByMinstry(Long id){
        return this.profileRepository.getMapDataByMinstry(id);
    }
    public List<MapData> getMapDataByAuthority(Long id){
        return this.profileRepository.getMapDataByAuthority(id);
    }
    public List<MapData> getMapDataByCommission(Long id){
        return this.profileRepository.getMapDataByCommission(id);
    }

    public List<RevisionDTO>  getProfileLog(Long id){
        Revisions<Integer, Profile> indList = profileRepository.findRevisions(id);
        List<Revision<Integer, Profile>> profiles = indList.getContent();

        List<RevisionDTO> dtos= new ArrayList<>();
        
        for(Revision revision: profiles){
                dtos.add(new RevisionDTO(revision.getEntity()));
        }

        return dtos;
    }

    public Boolean approveProfile(Long proId){
        Optional<Profile> objection = profileRepository.findById(proId);
        if (objection.isPresent()) {
            objection.get().setApprove(true);
            profileRepository.save(objection.get());
            return true;
        }
        return false;
    }


    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "left join public.employee_position po on po.id=pro.employee_position left join public.employee_status s on s.id=pro.employee_status left join public.ministry m on m.id=pro.first_ministry left join public.authority a on a.id=pro.first_authority left join public.ethnic et on et.id=pro.ethnic left join public.commission c on c.id=pro.first_commission left join public.province prov on prov.id=pro.original_province left join public.employee_military_grade mp on mp.id=pro.military_position";
        // To have first AND with no error
        String whereClause = " pro.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.profile pro", null, joinClause, whereClause, groupByClause, input);
    }

    public ProfileViewDto findById(Long id) {
        System.out.println("Profile.findById()" + id);
        Optional<Profile> objection = profileRepository.findById(id);
        if (objection.isPresent()) {
            System.out.println("Profile: " + objection);
            ProfileViewDto pdto = ProfileDtoMapper.ProfileMapViewDto(objection.get(), languageService);
            return pdto;
        }
        return null;
    }

    public ProfileDto findForEdit(Long id) {
        Optional<Profile> objection = profileRepository.findById(id);
        if (objection.isPresent()) {
            ProfileDto dto = ProfileDtoMapper.ProfileMapEditDto(objection.get(), languageService);
            return dto;
        }

        return null;
    }

    public Boolean update(Long id, ProfileDto dto) {
        Optional<Profile> objection = profileRepository.findById(id);
        if (objection.isPresent()) {
            Profile newProfile = ProfileDtoMapper.MapProfileDto(objection.get(), dto, this.countryService,
                    this.provinceService, this.districtService, this.ethnicService, this.nationalityService,
                    this.religionService, this.sectService, this.languageService, this.gradeService, this.statusService,
                    this.ministryService, this.authorityService, this.positionService, this.commissionService, this.genderService, 
                    this.nationalLanguageService, this.employeeMilitaryGradeService);
                    newProfile.setUpdatedBy(userService.getLoggedInUser().getUsername());
            Profile profileD  = profileRepository.save(newProfile);
            if(profileD.getDisable() != true){
                Map<String, Object> data = new HashMap<String, Object>();
                 data.put("profile", profileD);
                 this.profileJobService.update(data);
            }
            return true;
        }

        return false;

    }

    public Profile findByIdWithoutRelation(Long id) {
        Optional<Profile> objection = profileRepository.findById(id);
        if (objection.isPresent()) {
            return objection.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<Profile> profile = profileRepository.findById(id);
        if (profile.isPresent()) {
            Profile newProfile = profile.get();
            newProfile.setDeleted(true);
            profileRepository.save(newProfile);
            return true;
        }
        return false;
    }

    @Override
    public Profile save(Profile obj) {
        return this.profileRepository.save(obj);
    }

    @Override
    public Profile create(ProfileDto dto) {
        Profile newprofile = new Profile();
        Profile profile = ProfileDtoMapper.MapProfileDto(newprofile, dto, this.countryService, this.provinceService,
                this.districtService, this.ethnicService, this.nationalityService, this.religionService,
                this.sectService, this.languageService, this.gradeService, this.statusService, this.ministryService,
                this.authorityService, this.positionService, this.commissionService, this.genderService, 
                this.nationalLanguageService, this.employeeMilitaryGradeService);
                profile.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!profile.equals(null)) {
            Profile newProfile = profileRepository.save(profile);
            if (!newProfile.equals(null)) {
                String persianDate = changeDate.convertGregorianDateToPersianDate(profile.getYear());
                String year = persianDate.substring(0, 4);
                newProfile.setProfileCode("ARG-" + year + "-" + newProfile.getId());
                profileChecklistService.create(newProfile.getId());
                Profile profileP = profileRepository.save(newProfile);
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("profile", profileP);
                this.profileJobService.create(data);
                return profileP;
            }
        }

        return null;

    }

  public List<ProfileViewDto> getSearchCandidate(String value){
        List<Profile> profile = profileRepository.searchByValue(value);

        List<ProfileViewDto> map = new ArrayList<>();
        for(int i = 0 ;  i<profile.size(); i++){
            map.add(ProfileDtoMapper.ProfileMapViewDto(profile.get(i), languageService));
        }
        // ProfileViewDto mapper = ProfileDtoMapper.ProfileMapViewDto(profile, languageService);
        return map;

    }

    public Boolean updateProfileByTransfer(Transfer transfer, Long proId){
        Optional<Profile> objection = profileRepository.findById(proId);
        if (objection.isPresent()) {
            Profile profile = objection.get();
             profile.setPosition(transfer.getPosition());
             profile.setPositionTitle(transfer.getPositionTitle());
             profile.setMinistry(transfer.getMinistry());
             profile.setAuthority(transfer.getAuthority());
             profile.setCommission(transfer.getCommission());
             profileRepository.save(profile);
             return true;
        
        }
        return false;
    }


    public Boolean updateProfileByPanelty(Panelty panelty, Long proId){
        Optional<Profile> objection = profileRepository.findById(proId);  
        if (objection.isPresent()) {
            Profile profile = objection.get();
             profile.setPosition(panelty.getPosition() == null? null: panelty.getPosition());
             profile.setPositionTitle(panelty.getPositionTitle() == null? null: panelty.getPositionTitle());
             profile.setMinistry(panelty.getMinistry() == null? null: panelty.getMinistry());
             profile.setAuthority(panelty.getAuthority()== null? null: panelty.getAuthority());
             profile.setCommission(panelty.getCommission() == null? null: panelty.getCommission());
             profile.setDisable(true);
             profileRepository.save(profile);
             return true;
        
        }
        return false;

    }
}
