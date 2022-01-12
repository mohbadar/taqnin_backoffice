package com.nsia.officems._util;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UriCreator {

    @Value("${app.upload.profile}")
    private String uploadDir;

    public String imageUriCreator(String imageFileName) {
        return Paths.get("src", "main", "resources", "images", imageFileName).toUri().toString();
    }

    public String fontUriCreator(String fontFileName) {
        return Paths.get("src", "main", "resources", "handlebars", "fonts", fontFileName).toUri().toString();
    }

    public String profilePictureUriCreator(String imageFileName, Long profileId) {
        String returnPath;
        if (imageFileName != null) {
            returnPath = Paths.get(uploadDir, profileId.toString(), "profile", imageFileName).toUri().toString();
        } else {
            returnPath = "";
        }

        return returnPath;
    }

    // String avatarPhotoPath = upoadDoc + '/' + Id + "/profile/" + avaaterName;

    public String cssUriCreator(String FolderName, String cssFileName) {
        return Paths.get("webapps", "ROOT", "WEB-INF", "classes", "handlebars", FolderName, cssFileName).toUri()
                .toString();
    }
}
