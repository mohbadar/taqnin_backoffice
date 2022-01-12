package com.nsia.officems.odf.odf_order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Collection;
import java.util.Date;
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
import com.nsia.officems._admin.department.Department;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreement;
import com.nsia.officems.odf.odf_subject.OdfSubject;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "odf_order")
@Entity(name = "OdfOrder")
public class OdfOrder extends BaseEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odf_order_tbl_generator")
     @SequenceGenerator(name = "odf_order_tbl_generator", sequenceName = "odf_order_tbl_seq", allocationSize = 1)
     @Column(unique = true, updatable = false, nullable = false)
     private Long id;

     private String orderNumber;

     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
     private Date orderDate;

     @Column(columnDefinition = "TEXT")
     private String summary;

     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
     private Date implementationEndDate;

     @ManyToMany(fetch = FetchType.LAZY)
     @JoinTable(name = "odf_order_entity", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "entity_id", referencedColumnName = "id"))
     private Collection<Department> implementingDepartments;

     @ManyToMany(fetch = FetchType.LAZY)
     @JoinTable(name = "odf_order_sub_entity", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "entity_id", referencedColumnName = "id"))
     private Collection<Department> assistingDepartments;

     @ManyToOne
     @JoinColumn(name = "odf_subject_id", nullable = true, referencedColumnName = "id")
     private OdfSubject subject;
}

