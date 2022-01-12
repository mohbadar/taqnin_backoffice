package com.nsia.officems._identity.authentication.user;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._identity.authentication.group.Group;
import com.nsia.officems.taqnin.workflow.Workflow;
import com.nsia.officems._admin.department.Department;
import com.nsia.officems._admin.organization.Organization;

// @Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
// @ToString(exclude="groups, instanceComments, environments")
// @EqualsAndHashCode
@Entity(name = "User")
@Table(name = "user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_tbl_generator")
    @SequenceGenerator(name = "user_tbl_generator", sequenceName = "user_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column(name = "active", length = 1, nullable = false)
    private boolean active;

    @Column
    private String phoneNo;

    @NotNull
    @Size(min = 3, max = 100, message = "Username must at least 3 characters.")
    @Column(unique = true)
    private String username;

    // @Pattern( regexp = "(?=.*[0-9]).+" ),
    // @Pattern( regexp = "(?=.*[a-z]).+" ),
    // @Pattern( regexp = "(?=.*[A-Z]).+"),
    // @Pattern( regexp = "(?=.*[!@#$%^&*+=?-_()/\"\\.,<>~`;:]).+"),
    // @Pattern( regexp = "(?=\\S+$).+" )
    @NotNull
    @Size(min = 3, max = 100, message = "Password must at least 3 characters.")
    @JsonIgnore
    private String password;

    @Transient
    @JsonIgnore
    private String confirmPassword;

    @Column
    private String email;

    @Column
    private String preferences;

    @Column
    private String avatar;

    @Column
    private Boolean isClient;

    @Column
    private Boolean isWorkflow;

    @Column
    private Boolean isAdmin;

    @Column
    private Boolean deleted;
    

    @Column(name = "deleted_by")
    private Long deletedBy;

    @Column(name = "deleted_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    private LocalDateTime deletedAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // @Column(name = "token_expired", length = 1, nullable = false)
    // private boolean tokenExpired;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    @JsonIgnore
    private Collection<Group> groups = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "workflow_id", nullable = true, referencedColumnName = "id")
    private Workflow workflow;

    @OneToOne
    @JoinColumn(name = "entity_id", nullable = true, referencedColumnName = "id")
    private Organization entity;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true, referencedColumnName = "id")
    private Department department;

    // public User(String name, String email, String address) {
    // this.name = name;
    // this.email = email;
    // this.address = address;
    // }
    public User(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public User(Long id, String name, String address, String username, boolean active, String email, String avatar,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.username = username;
        this.active = active;
        this.email = email;

        this.avatar = avatar;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @JsonIgnore
    public Boolean isMatchingPasswords() {
        return this.password.equals(this.confirmPassword);
    }

    // @ManyToOne
    // @JoinColumn(name="job_id", nullable = true)
    // @JsonIgnore
    // private Job job;

    @Override
    public String toString() {
        return "User [name=" + name + ", active=" + active + ", email=" + email + ", created_at=" + createdAt
                + ", updated_at=" + updatedAt + "]";
    }
}
