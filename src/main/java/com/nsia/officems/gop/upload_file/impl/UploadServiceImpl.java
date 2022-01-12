package com.nsia.officems.gop.upload_file.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import javax.imageio.ImageIO;

import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.upload_file.UploadService;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.awt.image.BufferedImage;

@Service
public class UploadServiceImpl implements UploadService {
    @Value("${app.upload.profile}")
    private String uploadDir;
    private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    public Profile photo(long id, MultipartFile file) {

       Profile profile = profileService.findByIdWithoutRelation(id);
       if(!profile.equals(null)){
            String fileName = saveAttachment(file, profile.getId());
            profile.setAvatar(fileName);
            Profile newProfile = profileService.save(profile);
            profileChecklistService.update(newProfile.getId(), titles.getPhoto());
            return newProfile;
       }

       return null;

    }



    private String saveAttachment(MultipartFile file, Long id) {
        System.out.println("_________________________DATA: ");
        Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String fileName;
        // User user = userAuthService.getLoggedInUser();
        if (file != null) {
            System.out.println("INSIDE --------------- file: ");
            fileName = formatter.format(Calendar.getInstance().getTime()) + "_profile_" + file.getOriginalFilename();
            try {
                String saveDirectory = uploadDir + File.separator + File.separator + id + File.separator + "profile"
                        + File.separator;
                File test = new File(saveDirectory);
                if (!test.exists()) {
                    test.mkdirs();
                }

                byte[] bytes = file.getBytes();

                ByteArrayInputStream imageInputStream = new ByteArrayInputStream(bytes);
                BufferedImage image = ImageIO.read(imageInputStream);
                BufferedImage thumbnail = Scalr.resize(image, 200);

                File thumbnailOut = new File(saveDirectory + fileName);
                ImageIO.write(image, "png", thumbnailOut);

                System.out.println("Image Saved::: " + fileName);
                return fileName;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

}
