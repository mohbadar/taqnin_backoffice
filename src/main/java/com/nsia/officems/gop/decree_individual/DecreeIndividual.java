package com.nsia.officems.gop.decree_individual;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nsia.officems.gop.decree.Decree;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.gop.profile.Profile;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "DecreeIndividual")
@Table(name = "decree_individual")
@Where(clause = "deleted = false")
public class DecreeIndividual extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "decree_individual_tbl_generator")
    @SequenceGenerator(name = "decree_individual_tbl_generator", sequenceName = "decree_individual_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String fatherName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "decree_id", referencedColumnName = "id", nullable = false)
    private Decree decree;

    @ManyToOne()
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private Profile profile;

}
