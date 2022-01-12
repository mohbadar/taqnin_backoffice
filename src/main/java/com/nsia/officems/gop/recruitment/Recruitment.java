package com.nsia.officems.gop.recruitment;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recruitment")
@Entity(name = "Recruitment")
public class Recruitment {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recruitment_tbl_generator")
	@SequenceGenerator(name="recruitment_tbl_generator", sequenceName = "recruitment_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "surname")
	private String surname;
	
	@Column
    private String description;
    
    @Column
    private String email;
    
    @Column
    private String age;
    
    @Column
    private String salary;
    
    @Column
    private Boolean status;
    
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
    
}
