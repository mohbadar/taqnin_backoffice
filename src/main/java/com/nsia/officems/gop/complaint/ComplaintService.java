package com.nsia.officems.gop.complaint;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.nsia.officems.gop.complaint.requests.CreateComplaintRequest;
import com.nsia.officems.gop.complaint.requests.EditComplaintRequest;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.web.multipart.MultipartFile;

public interface ComplaintService {

    public List<Complaint> findAll();

    public Complaint findById(Long id);

    public Complaint create(CreateComplaintRequest createComplaintRequest);

    public Boolean delete(Long id);

    public boolean update(Long id, EditComplaintRequest editComplaintRequest);

    public List<String> findAllFileNames();

    public Boolean updateFileLocation(Long id, String fieldName, String fileLocation);

    public String saveAttachment(String uploadDirectory, MultipartFile file);

    public File downloadAttachment(Long id, String fieldName);

    public Boolean deleteAttachment(Long id, String fieldName);

    public Object getList(DataTablesInput input, Map<String, String> filters);

    public long count();

    public List<RevisionDTO> getComplaintLog(Long id);

}
