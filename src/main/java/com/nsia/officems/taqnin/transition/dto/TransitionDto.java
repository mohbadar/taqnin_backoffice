package com.nsia.officems.taqnin.transition.dto;

import lombok.Data;

@Data
public class TransitionDto {
 private long from_step_id;
 private  long to_step_id;
 private  String transition_number;
 private long workflow_id;
 private Boolean isLastTransition;
}
