package com.nsia.officems.odf.odf_agenda.odf_agenda_topic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._admin.department.Department;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.odf.odf_agenda.OdfAgenda;
import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreement;
import com.nsia.officems.odf.odf_presenters.OdfPresenter;
import com.nsia.officems.odf.odf_proposal.OdfProposal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "odf_agenda_topic")
@Entity(name = "OdfAgendaTopic")
public class OdfAgendaTopic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odf_agenda_topic_tbl_generator")
    @SequenceGenerator(name = "odf_agenda_topic_tbl_generator", sequenceName = "odf_agenda_topic_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String subject;

    @Column
    private String details;

    @Column
    private String presentDuration;

    @Column
    private String presentDurationType;

    @Column
    private String discussionDuration;

    @Column
    private String discussionDurationType;

    @Column
    private String inclusionReason;

    @ManyToOne
    @JoinColumn(name = "odf_agenda_id", nullable = true, referencedColumnName = "id")
    private OdfAgenda agenda;

    @OneToOne(targetEntity = OdfProposal.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "odf_proposal_id", referencedColumnName = "id")
    private OdfProposal odfProposal;
}
