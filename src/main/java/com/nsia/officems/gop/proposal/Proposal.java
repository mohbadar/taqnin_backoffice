package com.nsia.officems.gop.proposal;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.CascadeType;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._admin.authority.Authority;
import com.nsia.officems.gop.comment.Comment;
import com.nsia.officems._admin.commission.Commission;
import com.nsia.officems.gop.decree.Decree;
import com.nsia.officems.gop.documentType.DocumentType;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems._admin.ministry.Ministry;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.proposalIndividual.ProposalIndividual;
import com.nsia.officems.gop.suggestionStatus.SuggestionStatus;
import com.nsia.officems.gop.suggestionSubject.SuggestionSubject;
import com.nsia.officems.gop.suggestionType.SuggestionType;

import org.dom4j.Text;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author HPardess
 */
@Audited
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted is not true")
@Table(name = "proposal")
@Entity(name = "Proposal")
public class Proposal extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proposal_tbl_generator")
    @SequenceGenerator(name = "proposal_tbl_generator", sequenceName = "proposal_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String proposalNumber;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date proposalDate;

    private String incomingNumber;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date incomingDate;

    private String content;
    private String attachment;
    private String noteNumber;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date noteDate;

    private String firstName;
    private String lastName;
    private String fatherName;
    @Column(columnDefinition = "TEXT")
    private Boolean subject;

    @Column(columnDefinition = "TEXT")
    private String remarks;
    @Column(columnDefinition = "TEXT")
    private String details;

    @NotAudited
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comment comment;

    @NotAudited
    @OneToMany(mappedBy = "proposal", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Decree> decrees;

    @NotAudited
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "individual_proposal", joinColumns = @JoinColumn(name = "proposal_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"))
    @JsonIgnore
    private Collection<Profile> terrorGroups = new HashSet<>();

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "document_type_id", referencedColumnName = "id", nullable = true)
    private DocumentType documentType;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "suggestion_status_id", referencedColumnName = "id", nullable = true)
    private SuggestionStatus suggestionStatus;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "suggestion_type_id", referencedColumnName = "id", nullable = true)
    private SuggestionType suggestionType;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "morsal_ministry_id", referencedColumnName = "id", nullable = true)
    private Ministry morsalMinistry;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "morsal_authority_id", referencedColumnName = "id", nullable = true)
    private Authority morsalAuthority;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "morsal_commission_id", referencedColumnName = "id", nullable = true)
    private Commission morsalCommission;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "morsal_alaihai_ministry_id", referencedColumnName = "id", nullable = true)
    private Ministry morsalAlaihaiMinistry;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "morsal_alaihai_authority_id", referencedColumnName = "id", nullable = true)
    private Authority morsalAlaihaiAuthority;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "morsal_alaihai_commission_id", referencedColumnName = "id", nullable = true)
    private Commission morsalAlaihaiCommission;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "suggestion_subject_id", referencedColumnName = "id", nullable = true)
    private SuggestionSubject suggestionSubject;

    private String relevantStaff;

    private String copyTo;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date thirdCopyDate;

    private String thirdCopyNumber;

    private String expert;

    private boolean tashkeel;

    private String decreeNumber;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date decreeDate;

}
