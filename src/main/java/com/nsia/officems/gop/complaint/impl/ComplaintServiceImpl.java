package com.nsia.officems.gop.complaint.impl;

import com.nsia.officems.gop.Complaints_Docs_type.ComplaintDocsTypeService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._admin.commission.Commission;
import com.nsia.officems._admin.commission.CommissionRepository;
import com.nsia.officems._admin.commission.CommissionService;
import com.nsia.officems.gop.complaint.Complaint;
import com.nsia.officems.gop.complaint.ComplaintRepository;
import com.nsia.officems.gop.complaint.ComplaintService;
import com.nsia.officems.gop.complaint.requests.CreateComplaintRequest;
import com.nsia.officems.gop.complaint.requests.EditComplaintRequest;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Value("${app.upload.complaint}")
    private String uploadDir;

    private final ComplaintRepository _complaintRepository;
    private final ComplaintDocsTypeService _complaintDocsTypeService;
    private final DataTablesUtil _dataTablesUtil;

    @Autowired
    private UserService userService;

    public long count() {
        return _complaintRepository.count();
    }

    @Override
    public List<Complaint> findAll() {
        return _complaintRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Complaint findById(Long id) {
        Optional<Complaint> optionalObj = _complaintRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Complaint create(CreateComplaintRequest createComplaintRequest) {
        DateTimeChange changeDate = new DateTimeChange();

        Complaint newComplaint = new Complaint();
        newComplaint.setName(createComplaintRequest.getName());
        newComplaint.setLastName(createComplaintRequest.getLastName());
        newComplaint.setFatherName(createComplaintRequest.getFatherName());
        newComplaint.setEntryNumber(createComplaintRequest.getEntryNumber());
        newComplaint.setComplaintDocsType(
                _complaintDocsTypeService.findById(createComplaintRequest.getComplaintDocsType()));
        newComplaint.setComplaintType(createComplaintRequest.getComplaintType());
        newComplaint.setComplaintDate(createComplaintRequest.getComplaintDate() == null ? null
                : changeDate.convertPersianDateToGregorianDate(createComplaintRequest.getComplaintDate()));
        newComplaint.setAccused(createComplaintRequest.getAccused());
        newComplaint.setExplanations(createComplaintRequest.getExplanations());
        newComplaint.setProfileCode(createComplaintRequest.getProfileCode());

        newComplaint.setCreatedBy(userService.getLoggedInUser().getUsername());
        newComplaint.setDeleted(false);
        return _complaintRepository.save(newComplaint);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Complaint> complaint = _complaintRepository.findById(id);

        if (complaint.isPresent()) {
            Complaint complaint2 = complaint.get();
            complaint2.setDeletedBy(userService.getLoggedInUser().getUsername());
            complaint2.setDeleted(true);
            complaint2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            _complaintRepository.save(complaint2);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Long id, EditComplaintRequest editComplaintRequest) {
        DateTimeChange changeDate = new DateTimeChange();

        Optional<Complaint> complaintToBeUpdated = _complaintRepository.findById(id);
        if (complaintToBeUpdated.isEmpty()) {
            return false;
        }

        Complaint getComplaintToBeUpdated = complaintToBeUpdated.get();

        getComplaintToBeUpdated.setUpdatedBy(userService.getLoggedInUser().getUsername());

        getComplaintToBeUpdated.setName(editComplaintRequest.getName());
        getComplaintToBeUpdated.setLastName(editComplaintRequest.getLastName());
        getComplaintToBeUpdated.setFatherName(editComplaintRequest.getFatherName());
        getComplaintToBeUpdated.setEntryNumber(editComplaintRequest.getEntryNumber());
        getComplaintToBeUpdated
                .setComplaintDocsType(_complaintDocsTypeService.findById(editComplaintRequest.getComplaintDocsType()));
        getComplaintToBeUpdated.setComplaintType(editComplaintRequest.getComplaintType());
        getComplaintToBeUpdated.setComplaintDate(editComplaintRequest.getComplaintDate() == null ? null
                : changeDate.convertPersianDateToGregorianDate(editComplaintRequest.getComplaintDate()));
        getComplaintToBeUpdated.setAccused(editComplaintRequest.getAccused());
        getComplaintToBeUpdated.setExplanations(editComplaintRequest.getExplanations());
        getComplaintToBeUpdated.setProfileCode(editComplaintRequest.getProfileCode());

        _complaintRepository.save(getComplaintToBeUpdated);
        return true;
    }

    @Override
    public List<String> findAllFileNames() {
        return null;
    }

    @Override
    public Boolean updateFileLocation(Long id, String fieldName, String fileLocation) {
        Optional<Complaint> complaintToBeUpdated = _complaintRepository.findById(id);
        if (complaintToBeUpdated.isEmpty()) {
            return false;
        }

        Complaint editedComplaint = complaintToBeUpdated.get();

        if (fieldName.equals("objectionAttachment")) {
            editedComplaint.setObjectionAttachment(fileLocation);
        } else if (fieldName.equals("complaintToResponsibleAuthorityAttachment")) {
            editedComplaint.setComplaintToResponsibleAuthorityAttachment(fileLocation);
        } else if (fieldName.equals("complaintToBoardAttachment")) {
            editedComplaint.setComplaintToBoardAttachment(fileLocation);
        } else if (fieldName.equals("courtDecreeAttachment")) {
            editedComplaint.setCourtDecreeAttachment(fileLocation);
        } else if (fieldName.equals("committeeDecisionAttachment")) {
            editedComplaint.setCommitteeDecisionAttachment(fileLocation);
        } else if (fieldName.equals("responsibleAuthorityResponseAttachment")) {
            editedComplaint.setResponsibleAuthorityResponseAttachment(fileLocation);
        } else {
            return false;
        }

        _complaintRepository.save(editedComplaint);
        return true;
    }

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
    public File downloadAttachment(Long id, String fieldName) {
        Optional<Complaint> complaint = _complaintRepository.findById(id);
        if (complaint.isEmpty()) {
            return null;
        }

        String fileName;

        if (fieldName.equals("objectionAttachment")) {
            fileName = complaint.get().getObjectionAttachment();
        } else if (fieldName.equals("complaintToResponsibleAuthorityAttachment")) {
            fileName = complaint.get().getComplaintToResponsibleAuthorityAttachment();
        } else if (fieldName.equals("complaintToBoardAttachment")) {
            fileName = complaint.get().getComplaintToBoardAttachment();
        } else if (fieldName.equals("courtDecreeAttachment")) {
            fileName = complaint.get().getCourtDecreeAttachment();
        } else if (fieldName.equals("committeeDecisionAttachment")) {
            fileName = complaint.get().getCommitteeDecisionAttachment();
        } else if (fieldName.equals("responsibleAuthorityResponseAttachment")) {
            fileName = complaint.get().getResponsibleAuthorityResponseAttachment();
        } else {
            return null;
        }

        String file = uploadDir + "/" + id + "/" + fieldName + "/" + fileName;
        if (new File(file).exists()) {
            return new File(file);
        }
        return null;
    }

    @Override
    public Boolean deleteAttachment(Long id, String fieldName) {
        Optional<Complaint> complaint = _complaintRepository.findById(id);
        if (complaint.isEmpty()) {
            return null;
        }

        if (fieldName.equals("objectionAttachment")) {
            complaint.get().setObjectionAttachment(null);
        } else if (fieldName.equals("complaintToResponsibleAuthorityAttachment")) {
            complaint.get().setComplaintToResponsibleAuthorityAttachment(null);
        } else if (fieldName.equals("complaintToBoardAttachment")) {
            complaint.get().setComplaintToBoardAttachment(null);
        } else if (fieldName.equals("courtDecreeAttachment")) {
            complaint.get().setCourtDecreeAttachment(null);
        } else if (fieldName.equals("committeeDecisionAttachment")) {
            complaint.get().setCommitteeDecisionAttachment(null);
        } else if (fieldName.equals("responsibleAuthorityResponseAttachment")) {
            complaint.get().setResponsibleAuthorityResponseAttachment(null);
        } else {
            return false;
        }

        _complaintRepository.save(complaint.get());

        return false;
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "LEFT JOIN complaint_docs_type cdt on pc.complaint_doc_type = cdt.id";
        String whereClause = " pc.deleted is not true";
        String groupByClause = "";
        return _dataTablesUtil.getDataList("public.complaints pc", null, joinClause, whereClause, groupByClause, input);
    }

    public List<RevisionDTO> getComplaintLog(Long id) {
        Revisions<Integer, Complaint> indList = _complaintRepository.findRevisions(id);
        List<Revision<Integer, Complaint>> complaints = indList.getContent();

        List<RevisionDTO> dtos = new ArrayList<>();

        for (Revision revision : complaints) {
            dtos.add(new RevisionDTO(revision.getEntity()));
        }

        return dtos;
    }

}
