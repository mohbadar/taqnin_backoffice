package com.nsia.officems.gop.profile;

import java.util.List;
import java.util.Map;

import com.nsia.officems._util.MapData;
import com.nsia.officems.gop.panelty.Panelty;
import com.nsia.officems.gop.profile.Dto.ProfileDto;
import com.nsia.officems.gop.profile.Dto.ProfileViewDto;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.gop.profile_fired.ProfileFired;
import com.nsia.officems.gop.profile_promotion.ProfilePromotion;
import com.nsia.officems.gop.transfer_profile.Transfer;

import org.springframework.data.history.Revision;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface ProfileService {
    public Profile save(Profile obj);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public ProfileViewDto findById(Long id);
    public Profile findByIdWithoutRelation(Long id);
    public ProfileDto findForEdit(Long id);
    public Boolean update(Long id, ProfileDto dto); 
    public boolean delete(long id);
    public Profile create(ProfileDto dto);
    public List<ProfileViewDto> getSearchCandidate(String value);
    public Boolean updateProfileByTransfer(Transfer transfer, Long proId);
    public Boolean updateProfileByPanelty(Panelty panelty, Long proId);
    public long count();
    public long countActive();
    public List getProfileCountByEthnic();
    public List getProfileCountByGender();
    public List getProfileCountEducation();
    public List getProfileCountMinistry();
    public List getProfileCountAuthority();
    public List getProfileCountCommission();
    public List getProfileCountBySect();
    public List getProfileCountAge();
    public List<MapData> getMapData();
    public Boolean approveProfile(Long proId);
    public List getEthnicByMinistry(Long id);
    public List getEthnicByAuthority(Long id);
    public List getEthnicByCommission(Long id);
    public List getEthnicByAllMinistries();
    public List getEthnicByAllAuthorities();
    public List getEthnicByAllCommissions();

    public List getSectByAllMinistries();
    public List getSectByAllAuthories();
    public List getSectByAllCommission();
    public List getSectByMinistry(Long id);
    public List getSectByAuthority(Long id);
    public List getSectByCommission(Long id);

    public List getGenderByAllMinistries();
    public List getGenderByAllAuthorities();
    public List getGenderByAllCommission();

    public List getGenderByMinistry(Long id);
    public List getGenderByAuthority(Long id);
    public List getGenderByCommission(Long id);

    public List getAgeByAllMinistries();
    public List getAgeByAllAuthorities();
    public List getAgeByAllCommissions();

    public List getAgeByMinistry(Long id);
    public List getAgeByAuthority(Long id);
    public List getAgeByCommission(Long id);

    public List getEducationByAllMinistries();
    public List getEducationByAllAuthorities();
    public List getEducationByAllCommissions();

    public List getEducationByMinistry(Long id);
    public List getEducationByAuthority(Long id);
    public List getEducationByCommission(Long id);

    public List<MapData> getMapDataByAllMinistries();
    public List<MapData> getMapDataByAllAuthorities();
    public List<MapData> getMapDataByAllCommissions();
    public List<MapData> getMapDataByMinstry(Long proId);
    public List<MapData> getMapDataByAuthority(Long proId);
    public List<MapData> getMapDataByCommission(Long proId);

    public List<RevisionDTO>  getProfileLog(Long id);
}
