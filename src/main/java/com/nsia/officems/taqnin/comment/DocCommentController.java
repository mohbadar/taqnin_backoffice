package com.nsia.officems.taqnin.comment;
import com.google.gson.Gson;
import com.nsia.officems.taqnin.comment.dto.DocCommentDto;
import com.nsia.officems.taqnin.comment.dto.DocCommentMapper;
import com.nsia.officems.taqnin.document.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/taqnin/comments")
public class DocCommentController {

    @Autowired
    private DocCommentService service;
    
    @Autowired
    private DocumentService documentService;
    @GetMapping(path = {"/{id}"})
    public List<DocComment> findAllByDocument(@PathVariable("id") Long id){
        return service.findByDocumentId(id);
    }

    @GetMapping(path = {"single/{id}"})
    public DocComment findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping()
    public DocComment create(@RequestBody DocCommentDto dto ){
        return service.create(DocCommentMapper.MapDocCommentDto(new DocComment(), dto, documentService));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocComment> update(@PathVariable("id") Long id, @RequestBody String decision) {
        Gson g = new Gson();
        DocComment c = g.fromJson(decision, DocComment.class);
        if(c != null) {
            return ResponseEntity.ok(service.update(id, c));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

}
