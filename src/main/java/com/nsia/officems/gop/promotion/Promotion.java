package com.nsia.officems.gop.promotion;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.promotion_type.PromotionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author HPardess
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "promotion")
@Entity(name = "Promotion")
public class Promotion extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_tbl_generator")
	@SequenceGenerator(name="promotion_tbl_generator", sequenceName = "promotion_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;
    
    @Column
	private String rank;
    
	@Column
    private String step;

    @Column(name = "proposal_number")
    private Integer proposalNum;

    @Column(name = "proposal_date")
    //@Temporal(TemporalType.TIMESTAMP)
    private String proposalDate;

    @Column(name = "decree_number")
    private Integer decreeNum;

    @Column(name = "decree_date")
    private String decreeDate;

    @Column(name = "promotion_date")
    private String promotionDate;
    
    @ManyToOne
    @JoinColumn(name = "promotionType_id",referencedColumnName = "id", nullable = false)
    private PromotionType promotionType;
    
    // @ManyToOne
    // @JoinColumn(name = "profile_id", nullable = false)
    // @JsonIgnore
    // private Profile profiles;
   
}
