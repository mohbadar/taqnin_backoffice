package com.nsia.officems.odf.odf_proposal_document;

import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.odf.odf_proposal.OdfProposal;
import com.nsia.officems.odf.odf_proposal_document_type.OdfProposalDocType;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
@Entity(name = "odfproposaldoc")
@Table(name = "odf_proposal_doc")
public class OdfProposalDoc extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odf_proposal_doc_tbl_generator")
	@SequenceGenerator(name="odf_proposal_doc_tbl_generator", sequenceName = "odf_proposal_doc_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(columnDefinition = "varchar(800)")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = true, referencedColumnName = "id")
    private OdfProposalDocType type;

    @ManyToOne
    @JoinColumn(name = "proposal_id", nullable = true, referencedColumnName = "id")
    private OdfProposal proposal;
}
