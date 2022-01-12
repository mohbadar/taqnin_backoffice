package com.nsia.officems.gop.decree;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._admin.authority.Authority;
import com.nsia.officems._admin.commission.Commission;
import com.nsia.officems.gop.decree_document_type.DecreeDocumentType;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems._admin.ministry.Ministry;
import com.nsia.officems.gop.proposal.Proposal;

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Audited
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Decree")
@Table(name = "decree")
@Where(clause = "deleted = false")
public class Decree extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "decree_tbl_generator")
    @SequenceGenerator(name = "decree_tbl_generator", sequenceName = "decree_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String decreeNumber;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date decreeDate;

    @Column
    private String subject;
    @Column
    private String attachment;

    @NotAudited
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proposal_id", referencedColumnName = "id")
    private Proposal proposal;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "ministry_id", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Ministry ministry;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "authority_id", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Authority authority;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "commission_id", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Commission commission;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "decree_document_id", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private DecreeDocumentType decreeDocumentType;

}
