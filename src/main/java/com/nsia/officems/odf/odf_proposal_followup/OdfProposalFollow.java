package com.nsia.officems.odf.odf_proposal_followup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.odf.odf_proposal.OdfProposal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "odf_proposal_follow")
@Entity(name = "OdfProposalFollow")
public class OdfProposalFollow extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odf_proposal_follow_tbl_generator")
    @SequenceGenerator(name = "odf_proposal_follow_tbl_generator", sequenceName = "odf_proposal_follow_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    private String type;
    
    @Column(columnDefinition = "TEXT")
    private String summary;

    @ManyToOne
    @JoinColumn(name = "proposal_id", nullable = true, referencedColumnName = "id")
    private OdfProposal proposal;
    
}
