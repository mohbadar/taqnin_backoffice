package com.nsia.officems.gop.decree.impl;

import java.io.File;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems._admin.authority.AuthorityService;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems.gop.decree.DecreeRepository;
import com.nsia.officems.gop.decree.DecreeService;
import com.nsia.officems.gop.decree.Dto.DecreeDto;
import com.nsia.officems.gop.decree.Dto.DecreeDtoMapper;
import com.nsia.officems.gop.decree.Dto.DecreeViewDto;
import com.nsia.officems.gop.decree.Dto.ProposalDecreeDTO;
import com.nsia.officems.gop.decree_document_type.DecreeDocumentTypeService;
import com.nsia.officems.gop.decree_individual.DecreeIndividual;
import com.nsia.officems.gop.decree_individual.DecreeIndividualRepository;
import com.nsia.officems.gop.decree_individual.domain.DecreeIndividualDTO;
import com.nsia.officems.gop.decree_individual.mapper.DecreeIndividualMapper;
import com.nsia.officems.file.FileUploadUtil;
import com.nsia.officems.gop.language.LanguageService;
import com.nsia.officems._admin.ministry.MinistryService;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile.ProfileProjection;
import com.nsia.officems.gop.profile.ProfileRepository;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.gop.proposal.Proposal;
import com.nsia.officems.gop.proposal.ProposalRepository;
import com.nsia.officems.gop.decree.Decree;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DecreeServiceImpl implements DecreeService {

    @Autowired
    private DecreeRepository decreeRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private LanguageService languageService;

    @Autowired
    UserService userService;

    @Value("${app.upload.decree}")
    private String decrees;

    @Autowired
    private MinistryService ministryService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private CommissionService commissionService;

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private DecreeIndividualRepository decreeIndividualRepository;

    @Autowired
    private DecreeDocumentTypeService decreeDocumentTypeService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    public long count() {
        return this.decreeRepository.count();
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " LEFT JOIN decree_document_type ddt on dec.decree_document_id = ddt.id left join proposal p on dec.proposal_id = p.id LEFT JOIN public.commission c on c.id=dec.commission_id left join public.ministry m on m.id=dec.ministry_id left join authority a on a.id=dec.authority_id";
        String whereClause = " dec.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.decree dec", null, joinClause, whereClause, groupByClause, input);
    }

    public Map<String, Object> findById(Long id) {
        Map<String, Object> data = new HashMap<>();
        data.put("status", HttpStatus.NOT_FOUND);
        Optional<Decree> d = decreeRepository.findById(id);
        if (d.isPresent()) {
            data.put("status", HttpStatus.FOUND);
            Decree decree = d.get();
            Map<String, Object> suggestionStatus = new HashMap<>();
            Map<String, Object> suggestionType = new HashMap<>();
            Map<String, Object> documentType = new HashMap<>();
            Map<String, Object> ministry = new HashMap<>();
            Map<String, Object> proposalMinistry = new HashMap<>();
            Map<String, Object> authority = new HashMap<>();
            Map<String, Object> proposalAuthority = new HashMap<>();
            Map<String, Object> commission = new HashMap<>();
            Map<String, Object> proposalCommission = new HashMap<>();
            Map<String, Object> decreeDocumentType = new HashMap<>();
            ProposalDecreeDTO proposalDecreeDTO = null;
            if (decree.getProposal() != null) {
                Proposal proposal = decree.getProposal();
                if (proposal.getSuggestionStatus() != null) {
                    suggestionStatus.put("id", proposal.getSuggestionStatus().getId());
                    suggestionStatus.put("name", _lb(proposal.getSuggestionStatus().getNameEn(),
                            proposal.getSuggestionStatus().getNameDr(), proposal.getSuggestionStatus().getNamePs()));
                }
                if (proposal.getSuggestionType() != null) {
                    suggestionType.put("id", proposal.getSuggestionType().getId());
                    suggestionType.put("name", _lb(proposal.getSuggestionType().getNameEn(),
                            proposal.getSuggestionType().getNameDr(), proposal.getSuggestionType().getNamePs()));
                }
                if (proposal.getDocumentType() != null) {
                    documentType.put("id", proposal.getDocumentType().getId());
                    documentType.put("name", _lb(proposal.getDocumentType().getNameEn(),
                            proposal.getDocumentType().getNameDr(), proposal.getDocumentType().getNamePs()));
                }

                // if (proposal.getMinistry() != null) {
                // proposalMinistry.put("id", proposal.getDocumentType().getId());
                // proposalMinistry.put("name", _lb(proposal.getMinistry().getNameEn(),
                // proposal.getMinistry().getNameDr(), proposal.getMinistry().getNamePs()));
                // }
                // if (proposal.getAuthority() != null) {
                // proposalAuthority.put("id", proposal.getDocumentType().getId());
                // proposalAuthority.put("name", _lb(proposal.getAuthority().getNameEn(),
                // proposal.getAuthority().getNameDr(), proposal.getAuthority().getNamePs()));
                // }
                // if (proposal.getCommission() != null) {
                // proposalCommission.put("id", proposal.getDocumentType().getId());
                // proposalCommission.put("name", _lb(proposal.getCommission().getNameEn(),
                // proposal.getCommission().getNameDr(), proposal.getCommission().getNamePs()));
                // }

                proposalDecreeDTO = DecreeDtoMapper.mapProposalDecreeDTO(proposal, suggestionStatus, suggestionType,
                        documentType, proposalMinistry, proposalAuthority, proposalCommission);
            }

            if (decree.getMinistry() != null) {
                ministry.put("id", decree.getMinistry().getId());
                ministry.put("name", _lb(decree.getMinistry().getNameEn(), decree.getMinistry().getNameDr(),
                        decree.getMinistry().getNamePs()));
            }
            if (decree.getAuthority() != null) {
                authority.put("id", decree.getAuthority().getId());
                authority.put("name", _lb(decree.getAuthority().getNameEn(), decree.getAuthority().getNameDr(),
                        decree.getAuthority().getNamePs()));
            }
            if (decree.getCommission() != null) {
                commission.put("id", decree.getCommission().getId());
                commission.put("name", _lb(decree.getCommission().getNameEn(), decree.getCommission().getNameDr(),
                        decree.getCommission().getNamePs()));
            }

            if (decree.getDecreeDocumentType() != null) {
                decreeDocumentType.put("id", decree.getDecreeDocumentType().getId());
                decreeDocumentType.put("name", _lb(decree.getDecreeDocumentType().getNameEn(),
                        decree.getDecreeDocumentType().getNameDr(), decree.getDecreeDocumentType().getNamePs()));
                decreeDocumentType.put("code", decree.getDecreeDocumentType().getCode());
            }

            List<DecreeIndividualDTO> profiles = new ArrayList<DecreeIndividualDTO>();
            List<DecreeIndividual> decreeIndividuals = decreeIndividualRepository.findByDecree(decree);
            for (int i = 0; i < decreeIndividuals.size(); i++) {
                DecreeIndividualDTO decreeIndividualDTO = DecreeIndividualMapper
                        .mapDecreeIndividual(decreeIndividuals.get(i));
                profiles.add(decreeIndividualDTO);
            }

            DecreeViewDto dto = DecreeDtoMapper.mapDecreeViewDTO(decree, proposalDecreeDTO, ministry, authority,
                    commission, decreeDocumentType, profiles);
            data.put("decree", dto);
            return data;
        }
        return data;
    }

    private String _lb(String colEn, String colDr, String colPs) {
        String lang = "dr";
        switch (lang) {
        case "en":
            return colEn;
        case "ps":
            return colPs;
        case "dr":
            return colDr;
        default:
            return colEn;
        }
    }

    @Override
    public Decree save(DecreeDto dto, List<DecreeIndividualDTO> decreeIndividualDTOs, MultipartFile file) {
        Decree decreeObj = new Decree();
        if (dto.getId() != null) {
            decreeObj = decreeRepository.findById(dto.getId()).orElse(new Decree());
        }
        Proposal proposal = null;
        if (dto.getProposalNo() != null) {
            proposal = proposalRepository.findByProposalNumber(dto.getProposalNo());
        }
        Decree decree = DecreeDtoMapper.mapDecreeDTO(decreeObj, dto, languageService, this.ministryService,
                this.authorityService, this.commissionService, this.decreeDocumentTypeService, proposal);
        if (dto.getId() == null) {
            decree.setCreatedBy(userService.getLoggedInUser().getUsername());
            decree.setCreatedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        } else {
            decree.setUpdatedBy(userService.getLoggedInUser().getUsername());
        }
        // decree.setEnvSlug(userService.getCurrentEnv());
        if (!decree.equals(null)) {
            String fileName = fileUploadUtil.saveAttachment(file, decrees, dto.getDecreeNumber(), "decree");
            if (fileName != null) {
                decree.setAttachment(fileName);
            }

            Decree newDecree = decreeRepository.save(decree);
            decreeIndividualDTOs.forEach(decreeIndividualDto -> {
                Profile profile = null;
                DecreeIndividual decreeIndividual = new DecreeIndividual();
                if (decreeIndividualDto.getId() != null) {
                    profile = profileRepository.findById(decreeIndividualDto.getId()).get();
                }
                if (decreeIndividualDto.getDecreeIndividualNo() != null) {
                    decreeIndividual = decreeIndividualRepository.findById(decreeIndividualDto.getDecreeIndividualNo())
                            .orElse(new DecreeIndividual());
                }
                DecreeIndividual newDecreeIndividual = DecreeIndividualMapper.map(decreeIndividualDto, profile,
                        newDecree, decreeIndividual);
                decreeIndividualRepository.save(newDecreeIndividual);
            });

            return newDecree;
        }

        return null;

    }

    @Override
    public Boolean delete(Long id) {
        Optional<Decree> oDecree = decreeRepository.findById(id);

        if (oDecree.isPresent()) {
            Decree decree = oDecree.get();
            decree.setDeleted(true);
            decree.setDeletedBy(userService.getLoggedInUser().getUsername());
            decree.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            decreeRepository.save(decree);
            return true;
        }

        return false;
    }

    @Override
    public Map<String, Object> getProposalDetails(String proposalNo) {
        Map<String, Object> data = new HashMap<>();
        Proposal proposal = proposalRepository.findByProposalNumber(proposalNo);
        if (proposal != null) {
            data.put("status", HttpStatus.FOUND);
            data.put("proposalNumber", proposal.getProposalNumber());
            data.put("proposalDate", proposal.getProposalDate());
            // data.put("organization", proposal.getOrganization());

            Map<String, Object> documentType = new HashMap<>();
            Map<String, Object> suggestionStatus = new HashMap<>();
            Map<String, Object> suggestionType = new HashMap<>();
            if (proposal.getSuggestionStatus() != null) {
                suggestionStatus.put("id", proposal.getSuggestionStatus().getId());
                suggestionStatus.put("name", _lb(proposal.getSuggestionStatus().getNameEn(),
                        proposal.getSuggestionStatus().getNameDr(), proposal.getSuggestionStatus().getNamePs()));
            }
            if (proposal.getSuggestionType() != null) {
                suggestionType.put("id", proposal.getSuggestionType().getId());
                suggestionType.put("name", _lb(proposal.getSuggestionType().getNameEn(),
                        proposal.getSuggestionType().getNameDr(), proposal.getSuggestionType().getNamePs()));
            }
            if (proposal.getDocumentType() != null) {
                documentType.put("id", proposal.getDocumentType().getId());
                documentType.put("name", _lb(proposal.getDocumentType().getNameEn(),
                        proposal.getDocumentType().getNameDr(), proposal.getDocumentType().getNamePs()));
            }
            data.put("documentType", documentType);
            data.put("suggestionType", suggestionType);
            data.put("suggestionStatus", suggestionStatus);
            return data;
        }
        data.put("status", HttpStatus.NOT_FOUND);
        return data;
    }

    @Override
    public List<ProfileProjection> findProfilesByName(String term) {
        List<ProfileProjection> profiles = profileRepository.findByColumnsContaining(term);
        return profiles;
    }

    @Override
    public File downloadAttachment(String decreeNumber) throws Exception {
        Decree decree = decreeRepository.findByDecreeNumber(decreeNumber);
        String fileName = decree.getAttachment();
        String saveDirectory = decrees + "/" + decreeNumber + "/" + fileName;
        if (new File(saveDirectory).exists()) {
            return new File(saveDirectory);
        }

        return null;
    }

    @Override
    public List<RevisionDTO> getDecreeLog(Long id) {
        Revisions<Integer, Decree> indList = decreeRepository.findRevisions(id);
        List<Revision<Integer, Decree>> decrees = indList.getContent();

        List<RevisionDTO> dtos = new ArrayList<>();

        for (Revision revision : decrees) {
            dtos.add(new RevisionDTO(revision.getEntity()));
        }

        return dtos;
    }

}
