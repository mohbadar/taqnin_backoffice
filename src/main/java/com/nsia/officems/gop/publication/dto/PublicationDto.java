package com.nsia.officems.gop.publication.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationDto {

    private Long id;
    private Long type;
    private String title;
    private String publishDate;
    private String approvedAuthority;
    private String maktubShumara;
    private String maktubDate;
    private Long profile;
    
}
