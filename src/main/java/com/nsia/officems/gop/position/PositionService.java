package com.nsia.officems.gop.position;

import java.util.List;

public interface PositionService {
    public List<Position> findAll();
    public Position findById(Long id);
    public Position create(Position position);
    public Boolean delete(Long id);  
}
