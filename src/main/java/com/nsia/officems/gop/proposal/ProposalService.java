package com.nsia.officems.gop.proposal;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.gop.proposal.Dto.CreateProposalDto;
import com.nsia.officems.gop.proposal.Dto.ProposalDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.web.multipart.MultipartFile;

public interface ProposalService {
    public Proposal save(Proposal obj);

    public Object getList(DataTablesInput input, Map<String, String> filters);

    public Proposal findById(Long id);

    public boolean delete(long id);

    public Proposal update(CreateProposalDto createProposalDto, Long id);

    public Proposal create(CreateProposalDto createProposalDto);

    public File downloadAttachment(Long id) throws Exception;

    public Proposal deleteRecord(Long id);

    public long count();

    public String saveAttachment(String uploadDirectory, MultipartFile file);

    public Boolean updateFileLocation(Long id, String fileLocation);

    public Boolean deleteAttachment(Long id);

    public List<RevisionDTO> getProposalLog(Long id);
}
