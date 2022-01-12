package com.nsia.officems.odf.odf_proposal;

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
import com.nsia.officems._admin.shura.Shura;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.odf.odf_agenda.OdfAgenda;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopic;
import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreement;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "odf_proposal")
@Entity(name = "OdfProposal")
public class OdfProposal extends BaseEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odf_proposal_tbl_generator")
     @SequenceGenerator(name = "odf_proposal_tbl_generator", sequenceName = "odf_proposal_tbl_seq", allocationSize = 1)
     @Column(unique = true, updatable = false, nullable = false)
     private Long id;

     private String secretLevel;
     // ministries

     private String proposalNumber;

     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
     private Date proposalDate;

     @Column(columnDefinition = "TEXT")
     private String summary;

     @Column(columnDefinition = "TEXT")
     private String objective;

     private Boolean previousLink;

     @Column(columnDefinition = "TEXT")
     private String previousDecision;

     @Column(columnDefinition = "TEXT")
     private String lawArticle;

     // ministries
     private Boolean subAuthorityShare;
     @Column(columnDefinition = "TEXT")
     private String authorityImplementation;

     private Boolean governmentBudget;
     private Boolean forignBuget;
     private Boolean internationalAgree;

     @ManyToOne
     @JoinColumn(name = "authority_agreement", nullable = true, referencedColumnName = "id")
     private OdfAuthorityAgreement authorityAgreement;

     @Column(columnDefinition = "TEXT")
     private String ministriesImplementation;

     private String implementationScope;


     private String proposalPreResult;

     private Integer papers;

     @Column(columnDefinition = "TEXT")
     private String progressBasedOnLaw;

     private Boolean nextFollowUp;

     @Column(columnDefinition = "TEXT")
     private String reason;

     @Column(columnDefinition = "TEXT")
     private String proposalContent;

     @ManyToMany(fetch = FetchType.LAZY)
     @JoinTable(name = "odf_proposal_entity", joinColumns = @JoinColumn(name = "proposal_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "entity_id", referencedColumnName = "id"))
     private Collection<Department> entity;

     @ManyToMany(fetch = FetchType.LAZY)
     @JoinTable(name = "odf_proposal_sub_entity", joinColumns = @JoinColumn(name = "proposal_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "entity_id", referencedColumnName = "id"))
     private Collection<Department> subEntity;

     @ManyToOne
     @JoinColumn(name = "shura_id", nullable = true, referencedColumnName = "id")
     private Shura shura;

     // @OneToOne(targetEntity = OdfAgendaTopic.class, fetch = FetchType.EAGER)
     // @JoinColumn(nullable = true, name = "agenda_topic_id", referencedColumnName =
     // "id")
     // private OdfAgendaTopic odfAgendaTopic;
}
