package com.nsia.officems.taqnin.comment.dto;

import lombok.Data;

@Data
public class DocCommentDto {
    private String comment;
    private Long document_Id;
    private String comment_date;
}
