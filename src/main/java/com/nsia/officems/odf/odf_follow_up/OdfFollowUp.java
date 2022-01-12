package com.nsia.officems.odf.odf_follow_up;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.odf.odf_follow_up_type.OdfFollowUpType;
import com.nsia.officems.odf.odf_order.OdfOrder;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "odf_follow_up")
@Entity(name = "OdfFollowUp")
public class OdfFollowUp extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odf_follow_up_tbl_generator")
    @SequenceGenerator(name = "odf_follow_up_tbl_generator", sequenceName = "odf_follow_up_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String summary;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = true, referencedColumnName = "id")
    private OdfFollowUpType type;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = true, referencedColumnName = "id")
    private OdfOrder order;
}
