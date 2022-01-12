package com.nsia.officems.gop.upload_file;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping(value = "/photo")
    public ResponseEntity<Map<String, Object>> photo(
            @RequestParam(name = "avatar", required = false) MultipartFile file, @RequestParam("id") long id,
            HttpServletRequest request) throws Exception {
        System.out.println("******************** file upload **********");
        Map<String, Object> data = new HashMap<String, Object>();
        Profile profile = uploadService.photo(id, file);
        if(!profile.equals(null))
        {
            data.put("objection", profile);
        }
        
        return null;
    }

}
