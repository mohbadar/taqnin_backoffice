package com.nsia.officems.gop.mark_Medal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems.infrastructure.base.BaseEntity;

import com.nsia.officems.gop.profile.Profile;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "medal")
@Table(name = "Medal")
public class Medal extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medal_tbl_generator")
	@SequenceGenerator(name="medal_tbl_generator", sequenceName = "medal_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String medalType;
    private String approvedSource;
    private String maktubNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date maktubDate;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private Profile profile;
    
}
