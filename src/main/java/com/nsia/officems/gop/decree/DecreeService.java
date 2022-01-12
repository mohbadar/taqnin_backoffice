package com.nsia.officems.gop.decree;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.nsia.officems.gop.decree.Dto.DecreeDto;
import com.nsia.officems.gop.decree_individual.domain.DecreeIndividualDTO;
import com.nsia.officems.gop.profile.ProfileProjection;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.web.multipart.MultipartFile;

public interface DecreeService {
    public Object getList(DataTablesInput input, Map<String, String> filters);

    public Map<String, Object> findById(Long id);

    public Decree save(DecreeDto dto, List<DecreeIndividualDTO> decreeIndividualDTOs, MultipartFile file);

    public Boolean delete(Long id);

    Map<String, Object> getProposalDetails(String proposalNo);

    List<ProfileProjection> findProfilesByName(String term);

    public File downloadAttachment(String decreeNumber) throws Exception;

    public long count();

    public List<RevisionDTO> getDecreeLog(Long id);
}
