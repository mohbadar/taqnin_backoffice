package com.nsia.officems.odf.odf_agenda;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.gson.Gson;
import com.nsia.officems._util.FileConverterUtil;
import com.nsia.officems._util.HTMLToPDFCreator;
import com.nsia.officems.odf.odf_agenda.dto.OdfAgendaDto;
import com.nsia.officems.odf.odf_agenda.dto.OdfAgendaMapper;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopic;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopicService;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.dto.OdfAgendaTopicMapper;
import com.nsia.officems.odf.odf_presenters.OdfPresenter;
import com.nsia.officems.odf.odf_presenters.OdfPresenterService;
import com.nsia.officems.odf.odf_presenters.dto.OdfPresenterMapper;
import com.nsia.officems.odf.odf_proposal.OdfProposal;
import com.nsia.officems.odf.odf_proposal.OdfProposalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

@RestController
@RequestMapping("/api/odf/agendas")
public class OdfAgendaController {

    @Autowired
    private OdfAgendaService odfAgendaService;

    @Autowired
    private OdfAgendaTopicService odfAgendaTopicService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OdfProposalService odfProposalService;

    @Autowired
    private OdfPresenterService odfPresenterService;

    @Autowired
    private HTMLToPDFCreator htmlToPDFCreator;

    @Autowired
    private FileConverterUtil fileConverterUtil;

    @GetMapping()
    public List<OdfAgenda> findAll() {
        return odfAgendaService.findAll();
    }

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
            return odfAgendaService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(path = { "/{id}" })
    public OdfAgenda findById(@PathVariable("id") Long id) {
        return odfAgendaService.findById(id);
    }

    @PostMapping(value = "/update/{id}")
    public Boolean update(@PathVariable("id") Long id, @RequestBody OdfAgendaDto editOdfAgendaDto) {
        return odfAgendaService.update(id, editOdfAgendaDto);
    }

    @PostMapping(value = "/create")
    public OdfAgenda create(@RequestBody OdfAgendaDto createOdfAgendaDto) throws Exception {
        OdfAgenda createdAgenda = odfAgendaService.create(OdfAgendaMapper.MapAgendaDto(new OdfAgenda(),
                createOdfAgendaDto, odfAgendaService, odfAgendaTopicService, odfProposalService, odfPresenterService));
        if (createdAgenda != null && createOdfAgendaDto.getAgendaTopics() != null) {

            createOdfAgendaDto.getAgendaTopics().forEach((agendaTopic) -> {
                OdfAgendaTopic createdAgendaTopic = odfAgendaTopicService
                        .create(OdfAgendaTopicMapper.MapAgendaTopicDto(new OdfAgendaTopic(), agendaTopic,
                                odfAgendaService, odfProposalService, createdAgenda.getId()));
                if (createdAgendaTopic != null && agendaTopic.getPresenters() != null) {
                    agendaTopic.getPresenters().forEach((presenterName) -> {
                        OdfPresenter createdOdfPresenter = odfPresenterService.create(OdfPresenterMapper.MapPresentDto(
                                new OdfPresenter(), createdAgendaTopic.getId(), presenterName, odfAgendaTopicService));
                    });
                }
            });
        }
        return createdAgenda;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(odfAgendaService.delete(id));
    }

    @GetMapping(value = "/print/{agendaId}")
    public ResponseEntity<byte[]> printProfileAbstract(@PathVariable(name = "agendaId", required = false) Long id)
            throws IOException, InterruptedException, JSONException, ParseException {
        System.out.println("id from client*********************: " + id);

        // ProfileViewDto profileDto = profileService.findById(id);
        // Profile profile = profileService.findByIdWithoutRelation(id);
        // List<Education> educations = _educationService.findByProfile_id(id);
        // List<PrintEducationDto> educationsWithPersianDate = educations.stream()
        // .map(education -> _modelMapper.map(education,
        // PrintEducationDto.class)).collect(Collectors.toList());
        // educationsWithPersianDate.forEach(n -> {
        // if (n.getStartDate() != null) {
        // n.setStartDateString(_dateTimeChange.convertGregorianDateToPersianDate(n.getStartDate()));
        // }
        // if (n.getGraduationDate() != null) {
        // n.setGraduationDateString(_dateTimeChange.convertGregorianDateToPersianDate(n.getGraduationDate()));
        // }
        // });

        // ProfileJob latestJob = _profileJobService.findByLatestJob(id);
        // List<Reward> rewards = _rewardService.findByProfile_id(id);
        // List<Panelty> penalties = _penaltyService.findByProfile_id(id);
        // List<Publication> publications = _publicationService.findByProfile_id(id);
        // List<Medal> medals = _medalService.findByProfile_id(id);
        // List<MilitaryService> militaryService =
        // _militaryService.findByProfile_id(id);
        // List<ProfileJob> profileJobs = _profileJobService.findByProfile_id(id);
        // List<ProfileJobPrintDto> profileJobsWithPersianDate = profileJobs.stream()
        // .map(profileJob -> _modelMapper.map(profileJob,
        // ProfileJobPrintDto.class)).collect(Collectors.toList());
        // profileJobsWithPersianDate.forEach(p -> {
        // if (p.getMaktubDate() != null) {
        // p.setMaktubDateString(_dateTimeChange.convertGregorianDateToPersianDate(p.getMaktubDate()));
        // }
        // if (p.getEndDate() != null) {
        // p.setEndDateString(_dateTimeChange.convertGregorianDateToPersianDate(p.getEndDate()));
        // }
        // });
        // List<ProfilePromotion> promotions =
        // _profilePromotionService.findByProfile_id(id);

        // load html to handlebar
        TemplateLoader loader = new ClassPathTemplateLoader("/handlebars/Agenda", ".html");
        Handlebars handlebars = new Handlebars(loader);

        // Register Equality and String helpers with handlebar for date pattern
        // conversion
        handlebars.registerHelper(ConditionalHelpers.eq.name(), ConditionalHelpers.eq);
        StringHelpers.register(handlebars);

        Template template = handlebars.compile("Agenda");

        // Create dto
        // ProfileAbstractPrintDto profileAbstractPrintDto =
        // ProfileAbstractPrintDto.builder().profile(profileDto)
        // .latestJob(latestJob).rewards(rewards).penalties(penalties).publications(publications).medals(medals)
        // .militaryService(militaryService).languages(profile.getLanguage())
        // .profileJobs(profileJobsWithPersianDate)
        // .dateInHijri(_dateTimeChange.convertGregorianDateToPersianDate(Calendar.getInstance().getTime()))
        // .promotions(promotions).profilePictureUri(_uriCreator.profilePictureUriCreator(profile.getAvatar(),
        // id))
        // .participantInPoliticalOrganizations(_politicalPartyService.typeExists("سیاسی"))
        // .participantInSocialOrganizations(_politicalPartyService.typeExists("اجتماعی"))
        // .education(educationsWithPersianDate)
        // .praiseLetterCount(_profilePrintService.totalPraiseLetters(rewards))
        // .appreciationLetterCount(_profilePrintService.totalAppreciationDegrees(rewards))
        // .totalMedalCount(_profilePrintService.totalMedals(medals))
        // .totalNumberOfCashRewards(_profilePrintService.totalNumberOfCashRewards(rewards))
        // .tarekh(_dateTimeChange.convertGregorianDateToPersianDate(new Date()))
        // .logoImageUri(_uriCreator.imageUriCreator("Logo.png"))
        // .militaryMokalafiyat(_militaryService.typeExists("دوره مکلفیت"))
        // .militaryIhteyat(_militaryService.typeExists("دوره احتیاط"))
        // .total1stAppreciationDegrees(_profilePrintService.total1stAppreciationDegrees(rewards))
        // .total2ndAppreciationDegrees(_profilePrintService.total2ndAppreciationDegrees(rewards))
        // .total3rdAppreciationDegrees(_profilePrintService.total3rdAppreciationDegrees(rewards))
        // .salaryReductionCount(_profilePrintService.salaryReductionCount(penalties))
        // .conversionCount(_profilePrintService.conversionCount(penalties))
        // .warningCount(_profilePrintService.warningCount(penalties))
        // .contractTerminationCount(_profilePrintService.contractTerminationCount(penalties))
        // .adviceCount(_profilePrintService.adviceCount(penalties))
        // .fontBoldUri(_uriCreator.fontUriCreator("bahij_halvetic_bold.ttf"))
        // .fontLightUri(_uriCreator.fontUriCreator("bahij_halvetic_light.ttf"))
        // .fontRomanUri(_uriCreator.fontUriCreator("bahij_halvetic_roman.ttf")).build();

        String templateString = template.apply("Agenda");

        // Generate pdf
        String fileName = "Agenda";
        File generatedPdf = htmlToPDFCreator.generatePdf(templateString, fileName);

        // convert file to byte[]
        byte[] byteArrayOfFile = fileConverterUtil.fileToByteArray(generatedPdf);

        // Create header for downloading file
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + generatedPdf.getName());

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(byteArrayOfFile);
    }
}
