package com.nsia.officems.taqnin.comment.dto;

import java.util.Date;

import com.nsia.officems.taqnin.comment.DocComment;
import com.nsia.officems.taqnin.document.DocumentService;

public class DocCommentMapper {
    public static DocComment MapDocCommentDto(DocComment docComment,
            DocCommentDto dto, DocumentService documentService) {
        try {
            Date date = new Date();
            docComment.setComment(dto.getComment());
            docComment.setComment_date(date);
            docComment.setDocument(dto.getDocument_Id() == null ? null : documentService.findById(dto.getDocument_Id()));
            return docComment;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
