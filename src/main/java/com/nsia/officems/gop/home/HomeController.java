package com.nsia.officems.gop.home;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.nsia.officems.gop.complaint.ComplaintService;
import com.nsia.officems.gop.decree.DecreeService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_education.Dto.EducationDto;
import com.nsia.officems.gop.proposal.ProposalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/homes")
public class HomeController {

  @Autowired
  private ProfileService profileService;

  @Autowired
  private ProposalService proposalService;

  @Autowired
  private DecreeService decreeService;

  @Autowired
  private ComplaintService complaintService;

  @GetMapping(value = "/count")
  public ResponseEntity<Map<String, Object>> countData() {
    Map<String, Object> data = new HashMap<String, Object>();
    long profile = profileService.count();
    long proposal = proposalService.count();
    long decree = decreeService.count();
    long complaint = complaintService.count();
    long profileAcitve = profileService.countActive();

    data.put("profile", profile);
    data.put("proposal", proposal);
    data.put("decree", decree);
    data.put("complaint", complaint);
    data.put("profileAcitve", profileAcitve);
    return ResponseEntity.ok(data);
  }

  @GetMapping(path = { "/ethnic" })
  public List getProfileCountByEthnic() {
    return profileService.getProfileCountByEthnic();
  }

  @GetMapping(path = { "/gender" })
  public List getProfileCountByGender() {
    return profileService.getProfileCountByGender();
  }

  @GetMapping(path = { "/education" })
  public List getProfileEducationCount() {
    return profileService.getProfileCountEducation();
  }

  @GetMapping(path = { "/ministry" })
  public List getProfileMinistryCount() {
    return profileService.getProfileCountMinistry();
  }

  @GetMapping(path = { "/authority" })
  public List getProfileAuthorityCount() {
    return profileService.getProfileCountAuthority();
  }

  @GetMapping(path = { "/commission" })
  public List getProfileCommissionCount() {
    return profileService.getProfileCountCommission();
  }

  @GetMapping(path = { "/age" })
  public List getProfileAgeCount() {
    return profileService.getProfileCountAge();
  }

  @GetMapping(path = { "/sect" })
  public List getProfileSectCount() {
    return profileService.getProfileCountBySect();
  }

  @GetMapping("/ministryid")
  public List getEthnicByMinistry(@RequestParam("pId") Long pId) {
    return profileService.getEthnicByMinistry(pId);
  }

  @GetMapping("/authorityid")
  public List getEthnicByAuthority(@RequestParam("pId") Long pId) {
    return profileService.getEthnicByAuthority(pId);
  }

  @GetMapping("/commissionid")
  public List getEthnicByCommission(@RequestParam("pId") Long pId) {
    return profileService.getEthnicByCommission(pId);
  }

  @GetMapping(path = { "/allministries" })
  public List getEthnicByAllMinistries() {
    return profileService.getEthnicByAllMinistries();
  }

  @GetMapping(path = { "/allauthorities" })
  public List getEthnicByAllAuthorities() {
    return profileService.getEthnicByAllAuthorities();
  }

  @GetMapping(path = { "/allcommissions" })
  public List getEthnicByAllCommissions() {
    return profileService.getEthnicByAllCommissions();
  }

  @GetMapping(path = { "/sect/allministries" })
  public List getSectByAllMinistries() {
    return profileService.getSectByAllMinistries();
  }

  @GetMapping(path = { "/sect/allauthorities" })
  public List getSectByAllAuthories() {
    return profileService.getSectByAllAuthories();
  }

  @GetMapping(path = { "/sect/allcommissions" })
  public List getSectByAllCommission() {
    return profileService.getSectByAllCommission();
  }

  @GetMapping("/sect/ministryid")
  public List getSectByMinistry(@RequestParam("pId") Long pId) {
    return profileService.getSectByMinistry(pId);
  }

  @GetMapping("/sect/authorityid")
  public List getSectByAuthority(@RequestParam("pId") Long pId) {
    return profileService.getSectByAuthority(pId);
  }

  @GetMapping("/sect/commissionid")
  public List getSectByCommission(@RequestParam("pId") Long pId) {
    return profileService.getSectByCommission(pId);
  }

  @GetMapping(path = { "/gender/allministries" })
  public List getGenderByAllMinistries() {
    return profileService.getGenderByAllMinistries();
  }

  @GetMapping(path = { "/gender/allauthorities" })
  public List getGenderByAllAuthorities() {
    return profileService.getGenderByAllAuthorities();
  }

  @GetMapping(path = { "/gender/allcommissions" })
  public List getGenderByAllCommission() {
    return profileService.getGenderByAllCommission();
  }

  @GetMapping("/gender/ministryid")
  public List getGenderByMinistry(@RequestParam("pId") Long pId) {
    return profileService.getGenderByMinistry(pId);
  }

  @GetMapping("/gender/authorityid")
  public List getGenderByAuthority(@RequestParam("pId") Long pId) {
    return profileService.getGenderByAuthority(pId);
  }

  @GetMapping("/gender/commissionid")
  public List getGenderByCommission(@RequestParam("pId") Long pId) {
    return profileService.getGenderByCommission(pId);
  }

  @GetMapping(path = { "/age/allministries" })
  public List getAgeByAllMinistries() {
    return profileService.getAgeByAllMinistries();
  }

  @GetMapping(path = { "/age/allauthorities" })
  public List getAgeByAllAuthorities() {
    return profileService.getAgeByAllAuthorities();
  }

  @GetMapping(path = { "/age/allcommissions" })
  public List getAgeByAllCommissions() {
    return profileService.getAgeByAllCommissions();
  }

  @GetMapping("/age/ministryid")
  public List getAgeByMinistry(@RequestParam("pId") Long pId) {
    return profileService.getAgeByMinistry(pId);
  }

  @GetMapping("/age/authorityid")
  public List getAgeByAuthority(@RequestParam("pId") Long pId) {
    return profileService.getAgeByAuthority(pId);
  }

  @GetMapping("/age/commissionid")
  public List getAgeByCommission(@RequestParam("pId") Long pId) {
    return profileService.getAgeByCommission(pId);
  }

  @GetMapping(path = { "/education/allministries" })
  public List getEducationByAllMinistries() {
    return profileService.getEducationByAllMinistries();
  }

  @GetMapping(path = { "/education/allauthorities" })
  public List getEducationByAllAuthorities() {
    return profileService.getEducationByAllAuthorities();
  }

  @GetMapping(path = { "/education/allcommissions" })
  public List getEducationByAllCommissions() {
    return profileService.getEducationByAllCommissions();
  }

  @GetMapping("/education/ministryid")
  public List getEducationByMinistry(@RequestParam("pId") Long pId ) {
      return profileService.getEducationByMinistry(pId);
  }

  @GetMapping("/education/authorityid")
  public List getEducationByAuthority(@RequestParam("pId") Long pId ) {
      return profileService.getEducationByAuthority(pId);
  }

  @GetMapping("/education/commissionid")
  public List getEducationByCommission(@RequestParam("pId") Long pId ) {
      return profileService.getEducationByCommission(pId);
  }

}
