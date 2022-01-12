package com.nsia.officems.gop.upload_file;

import com.nsia.officems.gop.profile.Profile;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
        public Profile photo(long id, MultipartFile file);
}
