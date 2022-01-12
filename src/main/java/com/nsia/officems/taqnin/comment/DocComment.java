package com.nsia.officems.taqnin.comment;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "docComments")
@Entity(name = "DocComment")
public class DocComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "docComments_tbl_generator")
    @SequenceGenerator(name="docComments_tbl_generator", sequenceName = "docComments_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(columnDefinition = "text")
    private String comment;
    
    @Column
    private String postedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kabul")
    private Date comment_date ;
    @ManyToOne
    @JoinColumn(name = "document_id", nullable = true, referencedColumnName = "id")
    private Document document;

}
