package com.nsia.officems.taqnin.step;

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
@Table(name = "steps")
@Entity(name = "Step")
public class Step extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "steps_tbl_generator")
    @SequenceGenerator(name="steps_tbl_generator", sequenceName = "steps_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String namePs;
    @Column
    private String nameDr;
    @Column
    private String nameEn;

}
