package com.nsia.officems.taqnin.doc_department;
import com.google.gson.Gson;
import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.taqnin.doc_department.dto.DocDepartmentDto;
import com.nsia.officems.taqnin.doc_department.dto.DocDepartmentMapper;
import com.nsia.officems.taqnin.document.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/taqnin/docDepartments")
public class DocDepartmentController {

    @Autowired
    private DocDepartmentService service;
     
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DocumentService documentService;

    @GetMapping(path = {"/{id}"})
    public List<DocDepartment> findByDocumentId(@PathVariable("id") Long id){
        return service.findByDocumentId(id);
    }

    @GetMapping(path = {"single/{id}"})
    public DocDepartment findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping()
    public DocDepartment create(@RequestBody DocDepartmentDto dto ){
        return service.create(DocDepartmentMapper.docDepartmentMapDto(new DocDepartment(), departmentService, userService,documentService, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocDepartment> update(@PathVariable("id") Long id, @RequestBody String data) {
        Gson g = new Gson();
        DocDepartment c = g.fromJson(data, DocDepartment.class);
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
