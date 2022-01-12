package com.nsia.officems.gop.paneltyType;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.nsia.officems.infrastructure.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "PaneltyType")
@Table(name = "panelty_type")
public class PaneltyType extends BaseEntity{ 
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "panelty_type_tbl_generator")
	@SequenceGenerator(name="panelty_type_tbl_generator", sequenceName = "panelty_type_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
	private String code;

	@Column
	private String namePs;
	@Column
	private String nameDr;
	@Column
	private String nameEn;
    
}
