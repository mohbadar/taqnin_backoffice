package com.nsia.officems.taqnin.transition;

import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.taqnin.step.Step;
import com.nsia.officems.taqnin.workflow.Workflow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transitions")
@Entity(name = "Transition")
public class Transition extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "steps_tbl_generator")
    @SequenceGenerator(name="steps_tbl_generator", sequenceName = "steps_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @OneToOne(targetEntity = Step.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "from_step_id", referencedColumnName = "id")
    private Step fromStep;

    @OneToOne(targetEntity = Step.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "to_step_id", referencedColumnName = "id")
    private Step toStep;

    @ManyToOne
    @JoinColumn(name = "workflow_id", nullable = true, referencedColumnName = "id")
    private Workflow workflow;

    @Column
    private  String transition_number;

    @Column
    private Boolean isLastTransition;
}
