package com.nsia.officems.taqnin.announcement.main_announcement;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.taqnin.document.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "taqnin_main_announcement")
@Entity(name = "MainAnncouncement")
public class MainAnnouncement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taqnin_main_announcement_tbl_generator")
    @SequenceGenerator(name="decisions_tbl_generator", sequenceName = "taqnin_main_announcement_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String title;

    @Column
    private String publisher;

    @Column(columnDefinition = "text")
    private String details;

    @Column(nullable = true)
    private String attachment;

    @Column(nullable = true)
    private String totalViewers;

}
