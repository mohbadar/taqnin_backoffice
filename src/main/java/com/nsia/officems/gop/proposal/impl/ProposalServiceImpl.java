package com.nsia.officems.gop.proposal.impl;

import com.nsia.officems.gop.proposal.Proposal;
import com.nsia.officems.gop.proposal.ProposalRepository;
import com.nsia.officems.gop.proposal.ProposalService;
import com.nsia.officems.gop.proposal.Dto.CreateProposalDto;
import com.nsia.officems.gop.proposal.Dto.CreateProposalIndividualDto;
import com.nsia.officems.gop.proposal.Dto.ProposalDto;
import com.nsia.officems.gop.proposal.Dto.ProposalDtoMapper;
import com.nsia.officems.gop.proposalIndividual.ProposalIndividual;
import com.nsia.officems.gop.proposalIndividual.ProposalIndividualRepository;
import com.nsia.officems.gop.proposalIndividual.ProposalIndividualService;
import com.nsia.officems.gop.suggestionStatus.SuggestionStatusService;
import com.nsia.officems.gop.suggestionSubject.SuggestionSubject;
import com.nsia.officems.gop.suggestionSubject.SuggestionSubjectService;

import java.io.File;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserAuthService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._admin.authority.AuthorityService;
import com.nsia.officems.gop.comment.CommentService;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems.gop.documentType.DocumentTypeService;
import com.nsia.officems.file.FileUploadUtil;
import com.nsia.officems._admin.ministry.MinistryService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.awt.image.BufferedImage;

@Service
public class ProposalServiceImpl implements ProposalService {
    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    MinistryService ministryService;

    @Autowired
    AuthorityService authorityService;
    @Autowired
    CommissionService commissionService;

    @Autowired
    DocumentTypeService documentTypeService;

    @Autowired
    private ProposalIndividualService proposalIndividualService;

    @Autowired
    private SuggestionStatusService suggestionStatusService;

    @Autowired
    private SuggestionSubjectService suggestionSubjectService;

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProposalIndividualRepository proposalIndividualRepository;

    @Value("${app.individuals.dir}")
    private String individualsDir;

    @Value("${app.upload.proposal}")
    private String uploadDir;

    public long count() {
        return this.proposalRepository.count();
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "left join suggestion_type pt on pro.suggestion_type_id = pt.id LEFT JOIN document_type dt on pro.document_type_id = dt.id LEFT JOIN public.commission mc on mc.id=pro.morsal_commission_id "
                + "left join public.ministry mm on mm.id=pro.morsal_ministry_id left join public.authority ma on ma.id=pro.morsal_authority_id left join public.ministry mam on mam.id=pro.morsal_alaihai_ministry_id "
                + "left join public.commission mac on mac.id=pro.morsal_alaihai_commission_id left join public.authority maa on maa.id=pro.morsal_alaihai_authority_id";
        // To have first AND with no error
        String whereClause = " pro.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.proposal pro", null, joinClause, whereClause, groupByClause, input);
    }

    public Proposal findById(Long id) {
        System.out.println("Proposal.findById()" + id);
        Optional<Proposal> proposal = proposalRepository.findById(id);
        if (proposal.isPresent()) {
            System.out.println("Proposal: " + proposal);
            return proposal.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<Proposal> document = proposalRepository.findById(id);
        if (document.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public Proposal save(Proposal obj) {
        return this.proposalRepository.save(obj);
    }

    @Override
    public Proposal create(CreateProposalDto createProposalDto) {
        DateTimeChange changeDate = new DateTimeChange();

        Proposal newProposal = new Proposal();
        newProposal.setDocumentType(createProposalDto.getDocumentType() == null ? null
                : documentTypeService.findById(createProposalDto.getDocumentType()));
        newProposal.setIncomingNumber(createProposalDto.getIncomingNumber());
        newProposal.setIncomingDate(createProposalDto.getIncomingDate() == null ? null
                : changeDate.convertPersianDateToGregorianDate(createProposalDto.getIncomingDate()));
        newProposal.setProposalNumber(createProposalDto.getProposalNumber());
        newProposal.setProposalDate(createProposalDto.getProposalDate() == null ? null
                : changeDate.convertPersianDateToGregorianDate(createProposalDto.getProposalDate()));
        newProposal.setProposalNumber(createProposalDto.getProposalNumber());
        newProposal.setRelevantStaff(createProposalDto.getRelevantStaff());
        newProposal.setCopyTo(createProposalDto.getCopyTo());
        newProposal.setThirdCopyNumber(createProposalDto.getThirdCopyNumber());
        newProposal.setThirdCopyDate(createProposalDto.getThirdCopyDate() == null ? null
                : changeDate.convertPersianDateToGregorianDate(createProposalDto.getThirdCopyDate()));
        newProposal.setExpert(createProposalDto.getExpert());
        newProposal.setMorsalMinistry(createProposalDto.getMorsalMinistry() == null ? null
                : ministryService.findById(createProposalDto.getMorsalMinistry()));
        newProposal.setMorsalAuthority(createProposalDto.getMorsalAuthority() == null ? null
                : authorityService.findById(createProposalDto.getMorsalAuthority()));
        newProposal.setMorsalCommission(createProposalDto.getMorsalCommission() == null ? null
                : commissionService.findById(createProposalDto.getMorsalCommission()));
        newProposal.setMorsalAlaihaiMinistry(createProposalDto.getMorsalAlaihaiMinistry() == null ? null
                : ministryService.findById(createProposalDto.getMorsalAlaihaiMinistry()));
        newProposal.setMorsalAlaihaiAuthority(createProposalDto.getMorsalAlaihaiAuthority() == null ? null
                : authorityService.findById(createProposalDto.getMorsalAlaihaiAuthority()));
        newProposal.setMorsalAlaihaiCommission(createProposalDto.getMorsalAlaihaiCommission() == null ? null
                : commissionService.findById(createProposalDto.getMorsalAlaihaiCommission()));
        if (createProposalDto.getSuggestionStatus() != null) {
            newProposal.setSuggestionStatus(createProposalDto.getSuggestionStatus() == null ? null
                    : suggestionStatusService.findById(createProposalDto.getSuggestionStatus()));
        }
        newProposal.setNoteNumber(createProposalDto.getNoteNumber());
        newProposal.setNoteDate(createProposalDto.getNoteDate() == null ? null
                : changeDate.convertPersianDateToGregorianDate(createProposalDto.getNoteDate()));
        newProposal.setDecreeNumber(createProposalDto.getDecreeNumber());
        newProposal.setDecreeDate(createProposalDto.getDecreeDate() == null ? null
                : changeDate.convertPersianDateToGregorianDate(createProposalDto.getDecreeDate()));
        newProposal.setRemarks(createProposalDto.getRemarks());
        newProposal.setDetails(createProposalDto.getDetails());
        newProposal.setTashkeel(createProposalDto.isTashkeel());
        newProposal.setCreatedBy(userService.getLoggedInUser().getUsername());

        Proposal createdProposal = proposalRepository.save(newProposal);

        if (createdProposal.getId() != null) {
            if (!createProposalDto.isTashkeel()) {
                createProposalDto.getProfiles().forEach((profile) -> {
                    ProposalIndividual newProposalIndividual = new ProposalIndividual();
                    newProposalIndividual.setFirstName(profile.getFirstName());
                    newProposalIndividual.setLastName(profile.getLastName());
                    newProposalIndividual.setFatherName(profile.getFatherName());
                    newProposalIndividual.setSuggestionSubjects(profile.getSuggestionSubjects());
                    newProposalIndividual.setProposal(
                            createdProposal.getId() == null ? null : proposalService.findById(createdProposal.getId()));
                    if (profile.getProfileId() != null) {
                        newProposalIndividual
                                .setProfile(profileService.findByIdWithoutRelation(profile.getProfileId()));
                    }
                    proposalIndividualRepository.save(newProposalIndividual);
                });
            }
            return createdProposal;
        } else {
            return null;
        }
    }

    @Override
    public Proposal update(CreateProposalDto createProposalDto, Long id) {
        DateTimeChange changeDate = new DateTimeChange();

        Optional<Proposal> proposalToBeEdited = proposalRepository.findById(id);

        if (!proposalToBeEdited.isPresent()) {
            return null;
        }
        Proposal getProposalToBeEdited = proposalToBeEdited.get();
        getProposalToBeEdited.setDocumentType(createProposalDto.getDocumentType() == null ? null
                : documentTypeService.findById(createProposalDto.getDocumentType()));
        getProposalToBeEdited.setIncomingNumber(createProposalDto.getIncomingNumber());
        getProposalToBeEdited.setIncomingDate(createProposalDto.getIncomingDate() == null ? null
                : changeDate.convertPersianDateToGregorianDate(createProposalDto.getIncomingDate()));
        getProposalToBeEdited.setProposalNumber(createProposalDto.getProposalNumber());
        getProposalToBeEdited.setProposalDate(createProposalDto.getProposalDate() == null ? null
                : changeDate.convertPersianDateToGregorianDate(createProposalDto.getProposalDate()));
        getProposalToBeEdited.setProposalNumber(createProposalDto.getProposalNumber());
        getProposalToBeEdited.setRelevantStaff(createProposalDto.getRelevantStaff());
        getProposalToBeEdited.setCopyTo(createProposalDto.getCopyTo());
        getProposalToBeEdited.setThirdCopyNumber(createProposalDto.getThirdCopyNumber());
        getProposalToBeEdited.setThirdCopyDate(createProposalDto.getThirdCopyDate() == null ? null
                : changeDate.convertPersianDateToGregorianDate(createProposalDto.getThirdCopyDate()));
        getProposalToBeEdited.setExpert(createProposalDto.getExpert());
        getProposalToBeEdited.setMorsalMinistry(createProposalDto.getMorsalMinistry() == null ? null
                : ministryService.findById(createProposalDto.getMorsalMinistry()));
        getProposalToBeEdited.setMorsalAuthority(createProposalDto.getMorsalAuthority() == null ? null
                : authorityService.findById(createProposalDto.getMorsalAuthority()));
        getProposalToBeEdited.setMorsalCommission(createProposalDto.getMorsalCommission() == null ? null
                : commissionService.findById(createProposalDto.getMorsalCommission()));
        getProposalToBeEdited.setMorsalAlaihaiMinistry(createProposalDto.getMorsalAlaihaiMinistry() == null ? null
                : ministryService.findById(createProposalDto.getMorsalAlaihaiMinistry()));
        getProposalToBeEdited.setMorsalAlaihaiAuthority(createProposalDto.getMorsalAlaihaiAuthority() == null ? null
                : authorityService.findById(createProposalDto.getMorsalAlaihaiAuthority()));
        getProposalToBeEdited.setMorsalAlaihaiCommission(createProposalDto.getMorsalAlaihaiCommission() == null ? null
                : commissionService.findById(createProposalDto.getMorsalAlaihaiCommission()));
        if (createProposalDto.getSuggestionStatus() != null) {
            getProposalToBeEdited.setSuggestionStatus(createProposalDto.getSuggestionStatus() == null ? null
                    : suggestionStatusService.findById(createProposalDto.getSuggestionStatus()));
        }
        getProposalToBeEdited.setNoteNumber(createProposalDto.getNoteNumber());
        getProposalToBeEdited.setNoteDate(createProposalDto.getNoteDate() == null ? null
                : changeDate.convertPersianDateToGregorianDate(createProposalDto.getNoteDate()));
        getProposalToBeEdited.setDecreeNumber(createProposalDto.getDecreeNumber());
        getProposalToBeEdited.setDecreeDate(createProposalDto.getDecreeDate() == null ? null
                : changeDate.convertPersianDateToGregorianDate(createProposalDto.getDecreeDate()));
        getProposalToBeEdited.setRemarks(createProposalDto.getRemarks());
        getProposalToBeEdited.setDetails(createProposalDto.getDetails());
        getProposalToBeEdited.setTashkeel(createProposalDto.isTashkeel());
        getProposalToBeEdited.setUpdatedBy(userService.getLoggedInUser().getUsername());

        Proposal createdProposal = proposalRepository.save(getProposalToBeEdited);

        if (createdProposal.getId() != null) {
            if (createProposalDto.isTashkeel() != true) {
                // Check if Id
                List<CreateProposalIndividualDto> profiles = createProposalDto.getProfiles();

                createProposalDto.getProfiles().forEach((profile) -> {
                    ProposalIndividual newProposalIndividual = null;

                    if (profile.getId() != null) {
                        Optional<ProposalIndividual> proposalIndividual = proposalIndividualRepository
                                .findById(profile.getId());

                        newProposalIndividual = proposalIndividual.get();
                    }
                    if (newProposalIndividual == null) {
                        newProposalIndividual = new ProposalIndividual();
                    }
                    newProposalIndividual.setFirstName(profile.getFirstName());
                    newProposalIndividual.setLastName(profile.getLastName());
                    newProposalIndividual.setFatherName(profile.getFatherName());
                    newProposalIndividual.setSuggestionSubjects(profile.getSuggestionSubjects());
                    newProposalIndividual.setProposal(createdProposal);
                    newProposalIndividual.setDeleted(profile.getDeleted());
                    if (profile.getProfileId() != null) {
                        newProposalIndividual
                                .setProfile(profileService.findByIdWithoutRelation(profile.getProfileId()));
                    }
                    proposalIndividualRepository.save(newProposalIndividual);
                });
            }
            return createdProposal;
        } else {
            return null;
        }
    }

    private String saveIndividualAvatar(MultipartFile file, String id) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String fileName = null;
        // User user = userAuthService.getLoggedInUser();
        if (file != null) {
            fileName = fileUploadUtil.saveAttachment(file, individualsDir, id, "suggestion");
        }
        return fileName;
    }

    @Override
    public File downloadAttachment(Long id) throws Exception {
        Proposal proposal = proposalRepository.findById(id).get();
        String fileName = proposal.getAttachment();
        String saveDirectory = uploadDir + "/" + proposal.getId().toString() + "/" + fileName;
        if (new File(saveDirectory).exists()) {
            return new File(saveDirectory);
        }
        return null;
    }

    @Override
    public Proposal deleteRecord(Long id) {
        Optional<Proposal> opt = proposalRepository.findById(id);
        Proposal proposal = null;
        if (opt.isPresent()) {
            proposal = opt.get();
            proposal.setDeleted(true);
            proposalRepository.save(proposal);
        }

        return proposal;
    }

    @Override
    public String saveAttachment(String uploadDirectory, MultipartFile file) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String fileName = null;
        if (file != null) {
            fileName = formatter.format(Calendar.getInstance().getTime()) + "_" + file.getOriginalFilename();
            String saveDirectory = uploadDirectory;
            File test = new File(saveDirectory);
            if (!test.exists()) {
                test.mkdirs();
            }
            try {
                File f = new File(saveDirectory + "/" + fileName);
                // create the file
                if (!f.exists()) {

                    f.createNewFile();
                }
                FileOutputStream fout = new FileOutputStream(f);
                fout.write(file.getBytes());
                fout.close();
            } catch (Exception e) {

            }

        }
        return fileName;
    }

    @Override
    public Boolean updateFileLocation(Long id, String fileLocation) {
        Optional<Proposal> proposalToBeUpdated = proposalRepository.findById(id);
        if (proposalToBeUpdated.isEmpty()) {
            return false;
        }

        Proposal getProposalToBeUpdated = proposalToBeUpdated.get();
        getProposalToBeUpdated.setAttachment(fileLocation);
        proposalRepository.save(getProposalToBeUpdated);
        return true;
    }

    @Override
    public Boolean deleteAttachment(Long id) {
        Optional<Proposal> proposal = proposalRepository.findById(id);
        if (proposal.isEmpty()) {
            return null;
        }

        proposal.get().setAttachment(null);
        proposalRepository.save(proposal.get());

        return false;
    }

    @Override
    public List<RevisionDTO> getProposalLog(Long id) {
        Revisions<Integer, Proposal> indList = proposalRepository.findRevisions(id);
        List<Revision<Integer, Proposal>> proposals = indList.getContent();

        List<RevisionDTO> dtos = new ArrayList<>();

        for (Revision revision : proposals) {
            dtos.add(new RevisionDTO(revision.getEntity()));
        }

        return dtos;
    }
}
