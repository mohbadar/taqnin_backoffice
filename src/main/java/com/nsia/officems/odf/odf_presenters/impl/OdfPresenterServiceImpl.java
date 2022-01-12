package com.nsia.officems.odf.odf_presenters.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems.odf.odf_presenters.OdfPresenter;
import com.nsia.officems.odf.odf_presenters.OdfPresenterRepository;
import com.nsia.officems.odf.odf_presenters.OdfPresenterService;
import com.nsia.officems.odf.odf_presenters.dto.OdfPresenterDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class OdfPresenterServiceImpl implements OdfPresenterService {

    @Autowired
    private OdfPresenterRepository odfPresenterRepository;

    @Override
    public Object getList(DataTablesInput input, Map<String, String> filters) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OdfPresenter> findAll() {
        return odfPresenterRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public OdfPresenter findById(Long id) {
        Optional<OdfPresenter> optionalObj = odfPresenterRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public OdfPresenter create(OdfPresenter odfPresenter) {
        odfPresenter.setDeleted(false);
        return odfPresenterRepository.save(odfPresenter);
    }

    @Override
    public Boolean delete(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean update(Long id, OdfPresenterDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OdfPresenter> findByAgendaTopicId(Long id) {
        return odfPresenterRepository.findByAgendaTopic_id(id);
    }

}
