package com.nsia.officems.gop.comment.impl;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.gop.comment.Comment;
import com.nsia.officems.gop.comment.CommentRepository;
import com.nsia.officems.gop.comment.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    UserService userService;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        Optional<Comment> optionalObj = commentRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Comment create(Comment comment) {
       // comment.setCreatedBy(userService.getLoggedInUser().getUsername());
        return commentRepository.save(comment);
    }

   

    @Override
    public Boolean delete(Long id) {
        Optional<Comment> oComment = commentRepository.findById(id);

        if (oComment.isPresent()) {
            Comment comment = oComment.get();
           // comment.setDeleted(true);
           // comment.setDeletedBy(userService.getLoggedInUser().getUsername());
           // comment.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            commentRepository.save(comment);
            return true;
        }

        return false;
    }
    public Comment update(Long id, Comment comment){
        return null;
    }
}
