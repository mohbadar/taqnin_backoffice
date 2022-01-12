package com.nsia.officems.gop.profile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.gson.Gson;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._util.FileConverterUtil;
import com.nsia.officems._util.HTMLToPDFCreator;
import com.nsia.officems._util.UriCreator;
import com.nsia.officems.gop.mark_Medal.Medal;
import com.nsia.officems.gop.mark_Medal.MedalService;
import com.nsia.officems.gop.military_service.MilitaryService;
import com.nsia.officems.gop.military_service.MilitaryServiceS;
import com.nsia.officems.gop.panelty.Panelty;
import com.nsia.officems.gop.panelty.PaneltyService;
import com.nsia.officems.gop.political_party.PoliticalParty;
import com.nsia.officems.gop.political_party.PoliticalPartyService;
import com.nsia.officems.gop.profile.Dto.PrintEducationDto;
import com.nsia.officems.gop.profile.Dto.ProfileAbstractPrintDto;
import com.nsia.officems.gop.profile.Dto.ProfileDto;
import com.nsia.officems.gop.profile.Dto.ProfileJobPrintDto;
import com.nsia.officems.gop.profile.Dto.ProfileViewDto;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.gop.profile_education.Education;
import com.nsia.officems.gop.profile_education.EducationService;
import com.nsia.officems.gop.profile_education.Dto.EducationDto;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems.gop.profile_job.ProfileJobService;
import com.nsia.officems.gop.profile_promotion.ProfilePromotion;
import com.nsia.officems.gop.profile_promotion.ProfilePromotionService;
import com.nsia.officems.gop.promotion.Promotion;
import com.nsia.officems.gop.promotion.PromotionService;
import com.nsia.officems.gop.publication.Publication;
import com.nsia.officems.gop.publication.PublicationService;
import com.nsia.officems.gop.reward.Reward;
import com.nsia.officems.gop.reward.RewardService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.history.Revision;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private final HTMLToPDFCreator _htmlToPDFCreator;
    private final FileConverterUtil _fileConverterUtil;
    private final UriCreator _uriCreator;
    private final EducationService _educationService;
    private final ProfileJobService _profileJobService;
    private final RewardService _rewardService;
    private final PaneltyService _penaltyService;
    private final PublicationService _publicationService;
    private final MedalService _medalService;
    private final MilitaryServiceS _militaryService;
    private final ProfilePromotionService _profilePromotionService;
    private final DateTimeChange _dateTimeChange;
    private final PoliticalPartyService _politicalPartyService;
    private final ProfilePrintService _profilePrintService;
    private final ModelMapper _modelMapper;

    @Value("${app.upload.profile}")
    private String uploadDir;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private EducationService educationService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/list")
    public Object getFilesList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside GetFileList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return profileService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(value = "/{profileId}")
    public ResponseEntity<Map<String, Object>> findById(
            @PathVariable(name = "profileId", required = true) long objNumber) {
        System.out.println("Objection " + objNumber);
        Map<String, Object> data = new HashMap<String, Object>();
        ProfileViewDto objection = profileService.findById(objNumber);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/edit/{profileId}")
    public ResponseEntity<Map<String, Object>> findForEdit(@PathVariable(name = "profileId", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        ProfileDto objection = profileService.findForEdit(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id,
            @RequestParam("data") String data, HttpServletRequest request) throws Exception {
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        ProfileDto dto = g.fromJson(data, ProfileDto.class);
        return ResponseEntity.ok(profileService.update(id, dto));

    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Profile> create(@RequestParam("data") String data, HttpServletRequest request)
            throws Exception {
        Gson g = new Gson();
        ProfileDto dto = g.fromJson(data, ProfileDto.class);
        return ResponseEntity.ok(profileService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(profileService.delete(id));
    }

    @PostMapping("/search")
    public ResponseEntity<List<ProfileViewDto>> searchCandidate(@RequestBody String data) throws JSONException {
        JSONObject jso = new JSONObject(data);
        String value = jso.getString("input");
        return ResponseEntity.ok(profileService.getSearchCandidate(value));
    }

    @GetMapping(value = "/lasteducation/{profileId}")
    public ResponseEntity<Map<String, Object>> findLatestEducation(
            @PathVariable(name = "profileId", required = true) Long objNumber) {
        System.out.println("Objection " + objNumber);
        Map<String, Object> data = new HashMap<String, Object>();
        Education education = educationService.findbyEducationProfile(objNumber);
        data.put("objection", education);
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/approve/{profileId}")
    public Boolean approveProfile(@PathVariable(name = "profileId", required = true) Long objNumber) {
        return profileService.approveProfile(objNumber);
    }

    @GetMapping(value = "/{id}/history", produces = MediaType.ALL_VALUE)
    public ResponseEntity<List<RevisionDTO>> getHistory(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(profileService.getProfileLog(id));
    }

    @GetMapping(value = "/summary/print/{profileId}")
    public ResponseEntity<byte[]> printProfileSummary(@PathVariable(name = "profileId", required = false) Long id)
            throws IOException, InterruptedException, JSONException, ParseException {
        System.out.println("id from client*********************: " + id);
        // load html to handlebar
        TemplateLoader loader = new ClassPathTemplateLoader("/handlebars/ProfileSummary", ".html");
        Handlebars handlebars = new Handlebars(loader);

        Template template = handlebars.compile("ProfileSummary");
        String templateString = template.apply(null);

        // Generate pdf
        String fileName = "Summary";
        File generatedPdf = _htmlToPDFCreator.generatePdf(templateString, fileName);

        // convert file to byte[]
        byte[] byteArrayOfFile = _fileConverterUtil.fileToByteArray(generatedPdf);

        // Create header for downloading file
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + generatedPdf.getName());

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(byteArrayOfFile);

    }

    @GetMapping(value = "/abstract/print/{profileId}")
    public ResponseEntity<byte[]> printProfileAbstract(@PathVariable(name = "profileId", required = false) Long id)
            throws IOException, InterruptedException, JSONException, ParseException {
        System.out.println("id from client*********************: " + id);

        ProfileViewDto profileDto = profileService.findById(id);
        Profile profile = profileService.findByIdWithoutRelation(id);
        List<Education> educations = _educationService.findByProfile_id(id);
        List<PrintEducationDto> educationsWithPersianDate = educations.stream()
                .map(education -> _modelMapper.map(education, PrintEducationDto.class)).collect(Collectors.toList());
        educationsWithPersianDate.forEach(n -> {
            if (n.getStartDate() != null) {
                n.setStartDateString(_dateTimeChange.convertGregorianDateToPersianDate(n.getStartDate()));
            }
            if (n.getGraduationDate() != null) {
                n.setGraduationDateString(_dateTimeChange.convertGregorianDateToPersianDate(n.getGraduationDate()));
            }
        });

        ProfileJob latestJob = _profileJobService.findByLatestJob(id);
        List<Reward> rewards = _rewardService.findByProfile_id(id);
        List<Panelty> penalties = _penaltyService.findByProfile_id(id);
        List<Publication> publications = _publicationService.findByProfile_id(id);
        List<Medal> medals = _medalService.findByProfile_id(id);
        List<MilitaryService> militaryService = _militaryService.findByProfile_id(id);
        List<ProfileJob> profileJobs = _profileJobService.findByProfile_id(id);
        List<ProfileJobPrintDto> profileJobsWithPersianDate = profileJobs.stream()
                .map(profileJob -> _modelMapper.map(profileJob, ProfileJobPrintDto.class)).collect(Collectors.toList());
        profileJobsWithPersianDate.forEach(p -> {
            if (p.getMaktubDate() != null) {
                p.setMaktubDateString(_dateTimeChange.convertGregorianDateToPersianDate(p.getMaktubDate()));
            }
            if (p.getEndDate() != null) {
                p.setEndDateString(_dateTimeChange.convertGregorianDateToPersianDate(p.getEndDate()));
            }
        });
        List<ProfilePromotion> promotions = _profilePromotionService.findByProfile_id(id);

        // load html to handlebar
        TemplateLoader loader = new ClassPathTemplateLoader("/handlebars/Abstract", ".html");
        Handlebars handlebars = new Handlebars(loader);

        // Register Equality and String helpers with handlebar for date pattern
        // conversion
        handlebars.registerHelper(ConditionalHelpers.eq.name(), ConditionalHelpers.eq);
        StringHelpers.register(handlebars);

        Template template = handlebars.compile("Abstract");

        // Create dto
        ProfileAbstractPrintDto profileAbstractPrintDto = ProfileAbstractPrintDto.builder().profile(profileDto)
                .latestJob(latestJob).rewards(rewards).penalties(penalties).publications(publications).medals(medals)
                .militaryService(militaryService).languages(profile.getLanguage())
                .profileJobs(profileJobsWithPersianDate)
                .dateInHijri(_dateTimeChange.convertGregorianDateToPersianDate(Calendar.getInstance().getTime()))
                .promotions(promotions).profilePictureUri(_uriCreator.profilePictureUriCreator(profile.getAvatar(), id))
                .participantInPoliticalOrganizations(_politicalPartyService.typeExists("سیاسی"))
                .participantInSocialOrganizations(_politicalPartyService.typeExists("اجتماعی"))
                .education(educationsWithPersianDate)
                .praiseLetterCount(_profilePrintService.totalPraiseLetters(rewards))
                .appreciationLetterCount(_profilePrintService.totalAppreciationDegrees(rewards))
                .totalMedalCount(_profilePrintService.totalMedals(medals))
                .totalNumberOfCashRewards(_profilePrintService.totalNumberOfCashRewards(rewards))
                .tarekh(_dateTimeChange.convertGregorianDateToPersianDate(new Date()))
                .logoImageUri(_uriCreator.imageUriCreator("Logo.png"))
                .militaryMokalafiyat(_militaryService.typeExists("دوره مکلفیت"))
                .militaryIhteyat(_militaryService.typeExists("دوره احتیاط"))
                .total1stAppreciationDegrees(_profilePrintService.total1stAppreciationDegrees(rewards))
                .total2ndAppreciationDegrees(_profilePrintService.total2ndAppreciationDegrees(rewards))
                .total3rdAppreciationDegrees(_profilePrintService.total3rdAppreciationDegrees(rewards))
                .salaryReductionCount(_profilePrintService.salaryReductionCount(penalties))
                .conversionCount(_profilePrintService.conversionCount(penalties))
                .warningCount(_profilePrintService.warningCount(penalties))
                .contractTerminationCount(_profilePrintService.contractTerminationCount(penalties))
                .adviceCount(_profilePrintService.adviceCount(penalties))
                .fontBoldUri(_uriCreator.fontUriCreator("bahij_halvetic_bold.ttf"))
                .fontLightUri(_uriCreator.fontUriCreator("bahij_halvetic_light.ttf"))
                .fontRomanUri(_uriCreator.fontUriCreator("bahij_halvetic_roman.ttf")).build();

        String templateString = template.apply(profileAbstractPrintDto);

        // Generate pdf
        String fileName = "Abstract";
        File generatedPdf = _htmlToPDFCreator.generatePdf(templateString, fileName);

        // convert file to byte[]
        byte[] byteArrayOfFile = _fileConverterUtil.fileToByteArray(generatedPdf);

        // Create header for downloading file
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + generatedPdf.getName());

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(byteArrayOfFile);

    }

}