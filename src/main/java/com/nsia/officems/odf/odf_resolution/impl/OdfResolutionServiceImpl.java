package com.nsia.officems.odf.odf_resolution.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._admin.shura.ShuraService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.odf.odf_resolution.OdfResolution;
import com.nsia.officems.odf.odf_resolution.OdfResolutionRepository;
import com.nsia.officems.odf.odf_resolution.OdfResolutionService;
import com.nsia.officems.odf.odf_resolution.dto.OdfResolutionDto;
import com.nsia.officems.odf.odf_resolution.dto.OdfResolutionMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class OdfResolutionServiceImpl implements OdfResolutionService {

    @Autowired
    private OdfResolutionRepository odfResolutionRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private ShuraService shuraService;

    @Autowired
    private UserService userService;

    @Override
    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "left join public.shura shura on shura.id=resolution.shura_id";
        // To have first AND with no error
        String whereClause = " resolution.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.odf_resolution resolution", null, joinClause, whereClause,
                groupByClause, input);
    }

    @Override
    public List<OdfResolution> findAll() {
        return odfResolutionRepository.findAll();
    }

    @Override
    public OdfResolution findById(Long id) {
        Optional<OdfResolution> optionalObj = odfResolutionRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public OdfResolution create(OdfResolution resolution) {
        resolution.setDeleted(false);
        return odfResolutionRepository.save(resolution);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<OdfResolution> odfResolution = odfResolutionRepository.findById(id);

        if (odfResolution.isPresent()) {
            OdfResolution odfResolutionToBeDeleted = odfResolution.get();
            odfResolutionToBeDeleted.setDeleted(true);
            odfResolutionToBeDeleted
                    .setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            odfResolutionRepository.save(odfResolutionToBeDeleted);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Long id, OdfResolutionDto odfResolutionDto) {
        Optional<OdfResolution> odfResolution = odfResolutionRepository.findById(id);
        if (odfResolution.isPresent()) {
            OdfResolution odfResolutionToBeUpdated = odfResolution.get();
            OdfResolutionMapper.MapResolutionDto(odfResolutionToBeUpdated, odfResolutionDto, shuraService);
            odfResolutionToBeUpdated.setUpdatedBy(userService.getLoggedInUser().getUsername());
            odfResolutionToBeUpdated
                    .setUpdatedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            odfResolutionRepository.save(odfResolutionToBeUpdated);
            return true;
        }
        return false;
    }
}
