package com.nsia.officems.taqnin.comment.impl;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.taqnin.comment.DocComment;
import com.nsia.officems.taqnin.comment.DocCommentRepository;
import com.nsia.officems.taqnin.comment.DocCommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocCommentServiceImpl implements DocCommentService {
    @Autowired
    private DocCommentRepository repo;

    @Autowired
    UserService userService;

    @Override
    public List<DocComment> findByDocumentId(Long id) {
        return repo.findByDocument_idAndDeletedFalse(id);
    }

    @Override
    public DocComment findById(Long id) {
        Optional<DocComment> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public DocComment create(DocComment comment) {
        comment.setDeleted(false);
        comment.setCreatedBy(userService.getLoggedInUser().getUsername());
        comment.setPostedBy(userService.getLoggedInUser().getUsername());
        return repo.save(comment);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<DocComment> oDecision = repo.findById(id);
        if (oDecision.isPresent()) {
            DocComment Decision = oDecision.get();
            Decision.setDeleted(true);
            Decision.setDeletedBy(userService.getLoggedInUser().getUsername());
            Decision.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(Decision);
            return true;
        }

        return false;
    }

    @Override
    public DocComment update(Long id, DocComment comment) {
        if(id != null ) {
            Optional<DocComment> newcomment = repo.findById(id);
            if(newcomment.isPresent())
            {   DocComment updatedComment = newcomment.get();
                updatedComment.setComment(comment.getComment());
                return repo.save(updatedComment);
            }
        
        }

        return null;
    }
}
