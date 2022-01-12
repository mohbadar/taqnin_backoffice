package com.nsia.officems.taqnin.announcement;

import com.nsia.officems.infrastructure.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Announcement")
@Table(name = "announcement")
public class Announcement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taqnin_announcement_tbl_generator")
    @SequenceGenerator(name = "taqnin_announcement_tbl_generator", sequenceName = "taqnin_announcement_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String title;

    @Column
    private String publisher;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column
    private String attachment;

    @Column(nullable = true)
    private Integer totalViewers; 

}
