package com.nsia.officems.taqnin.decision;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/taqnin/decisions")
public class DecisionController {

    @Autowired
    private DecisionService service;

    @PreAuthorize("hasAuthority('STATUS_LIST')")
    @GetMapping()
    public List<Decision> findAll(){
        return service.findAll();
    }

    @PreAuthorize("hasAuthority('STATUS_VIEW')")
    @GetMapping(path = {"/{id}"})
    public Decision findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PreAuthorize("hasAuthority('STATUS_CREATE')")
    @PostMapping()
    public Decision create(@RequestBody Decision decision ){
        return service.create(decision);
    }

    @PreAuthorize("hasAuthority('STATUS_EDIT')")
    @PutMapping("/{id}")
    public ResponseEntity<Decision> update(@PathVariable("id") Long id, @RequestBody String decision) {
        Gson g = new Gson();
        Decision c = g.fromJson(decision, Decision.class);
        if(c != null) {
            return ResponseEntity.ok(service.update(id, c));
        }
        return null;
    }

    @PreAuthorize("hasAuthority('STATUS_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

}
