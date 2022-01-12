package com.nsia.officems.gop.document_upload_profile;


import com.nsia.officems.gop.document_upload_type_profile.DocumentUploadType;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.gop.profile.Profile;
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
@Entity(name = "DocumentUpload")
@Table(name = "document_upload")
public class DocumentUpload extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_upload_tbl_generator")
	@SequenceGenerator(name="document_upload_tbl_generator", sequenceName = "document_upload_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(columnDefinition = "varchar(800)")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = true, referencedColumnName = "id")
    private DocumentUploadType type;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private Profile profile;
    
}
