package com.nsia.officems.taqnin.taqnin_resolution.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._admin.shura.ShuraService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.taqnin.taqnin_resolution.TaqninResolution;
import com.nsia.officems.taqnin.taqnin_resolution.TaqninResolutionRepository;
import com.nsia.officems.taqnin.taqnin_resolution.TaqninResolutionService;
import com.nsia.officems.taqnin.taqnin_resolution.dto.TaqninResolutionDto;
import com.nsia.officems.taqnin.taqnin_resolution.dto.TaqninResolutionMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class TaqninResolutionServiceImpl implements TaqninResolutionService {

    @Autowired
    private TaqninResolutionRepository TaqninResolutionRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private ShuraService shuraService;

    @Autowired
    private UserService userService;

    @Override
    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "";
        // To have first AND with no error
        String whereClause = " resolution.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.taqnin_resolution resolution", null, joinClause, whereClause,
                groupByClause, input);
    }

    @Override
    public List<TaqninResolution> findAll() {
        return TaqninResolutionRepository.findAll();
    }

    @Override
    public TaqninResolution findById(Long id) {
        Optional<TaqninResolution> optionalObj = TaqninResolutionRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public TaqninResolution create(TaqninResolution resolution) {
        resolution.setDeleted(false);
        return TaqninResolutionRepository.save(resolution);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<TaqninResolution> TaqninResolution = TaqninResolutionRepository.findById(id);

        if (TaqninResolution.isPresent()) {
            TaqninResolution TaqninResolutionToBeDeleted = TaqninResolution.get();
            TaqninResolutionToBeDeleted.setDeleted(true);
            TaqninResolutionToBeDeleted
                    .setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            TaqninResolutionRepository.save(TaqninResolutionToBeDeleted);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Long id, TaqninResolutionDto TaqninResolutionDto) {
        Optional<TaqninResolution> TaqninResolution = TaqninResolutionRepository.findById(id);
        if (TaqninResolution.isPresent()) {
            TaqninResolution TaqninResolutionToBeUpdated = TaqninResolution.get();
            TaqninResolutionMapper.MapResolutionDto(TaqninResolutionToBeUpdated, TaqninResolutionDto, shuraService);
            TaqninResolutionToBeUpdated.setUpdatedBy(userService.getLoggedInUser().getUsername());
            TaqninResolutionToBeUpdated
                    .setUpdatedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            TaqninResolutionRepository.save(TaqninResolutionToBeUpdated);
            return true;
        }
        return false;
    }
}
