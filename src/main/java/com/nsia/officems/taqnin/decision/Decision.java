package com.nsia.officems.taqnin.decision;

import com.nsia.officems.infrastructure.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "decisions")
@Entity(name = "Decision")
public class Decision extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "decisions_tbl_generator")
    @SequenceGenerator(name="decisions_tbl_generator", sequenceName = "decisions_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String namePs;
    @Column
    private String nameDr;
    @Column
    private String nameEn;
}
