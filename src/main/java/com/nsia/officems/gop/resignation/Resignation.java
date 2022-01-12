package com.nsia.officems.gop.resignation;

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

/**
 *
 * @author HPardess
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "resignation")
@Entity(name = "Resignation")
public class Resignation{
    @Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resignation_tbl_generator")
	// @SequenceGenerator(name="profile_tbl_generator", sequenceName = "resignation_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "office_name")
	private String office_name;
    
    @Column(name = "first_name")
    private String firstName;
    
	@Column
    private String description;
    
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
