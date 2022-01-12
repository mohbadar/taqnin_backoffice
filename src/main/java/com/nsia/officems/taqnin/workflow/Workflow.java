package com.nsia.officems.taqnin.workflow;


import com.nsia.officems.infrastructure.base.BaseEntity;

import com.nsia.officems.taqnin.step.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "taqnin_workflows")
@Entity(name = "TaqninWorkflow")
public class Workflow extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taqnin_workflows_tbl_generator")
    @SequenceGenerator(name = "taqnin_workflows_tbl_generator", sequenceName = "taqnin_workflows_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;
    @Column
    private String namePs;
    @Column
    private String nameDr;
    @Column
    private String nameEn;

    @Column
    private Integer serialNo;

    @Column
    private Integer processDays;

    // @ManyToMany(fetch = FetchType.LAZY)
    // @JoinTable(name = "workflow_step_entity", joinColumns = @JoinColumn(name = "workflow_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "entity_id", referencedColumnName = "id"))
    // private Collection<Step> steps;

}


