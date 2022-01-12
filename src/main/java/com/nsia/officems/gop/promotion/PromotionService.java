package com.nsia.officems.gop.promotion;

import java.util.List;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;


public interface PromotionService {
  //  public Promotion save(Promotion promotion);
    public List<Promotion> findAll();
    public Promotion findById(Long id);
    public boolean delete(long id);
	  public Promotion create(Promotion promotion);
    
}


