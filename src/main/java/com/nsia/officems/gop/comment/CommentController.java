package com.nsia.officems.gop.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;


@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping()
    public List<Comment> findAll(){
        return commentService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Comment findById(@PathVariable("id") Long id){
        return commentService.findById(id);
        
    }

  

    @PostMapping()
    public Comment create(@RequestBody Comment comment){
        System.out.println("Create Controller");
        return commentService.create(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commentService.delete(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable("id") Long id, @RequestBody String comment) {
        Gson g = new Gson();
        Comment c = g.fromJson(comment, Comment.class);
        if(c != null) {
            return ResponseEntity.ok(commentService.update(id, c));
        }
        return null;
    }


}
