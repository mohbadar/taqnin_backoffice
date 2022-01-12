package com.nsia.officems.taqnin.doc_department;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._admin.department.Department;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.taqnin.document.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "docDepartments")
@Entity(name = "DocDepartment")
public class DocDepartment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "docDepartments_tbl_generator")
    @SequenceGenerator(name = "docDepartments_tbl_generator", sequenceName = "docDepartments_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date receiveDate;
     
    @Column
    private String description;

    @OneToOne(targetEntity = Department.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "department_id", referencedColumnName = "id")
    private Department department;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "user_id", referencedColumnName = "id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "document_id", nullable = true, referencedColumnName = "id")
    private Document document;
}
