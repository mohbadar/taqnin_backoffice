package com.nsia.officems.file;

import java.io.File;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/*
 * This class is to be used for all the file/attachment uploads
 * 
 * 
 * Author: Jalil Haidari
 **/

@Service
public class FileUploadUtil {

    /*
     * 
     * function to save the attachment to the directory which is being taken from
     * properties file
     * 
     * @Params file: the file to be saved
     * 
     * @params uploadDirectory: the directory in which we want the file to be saved
     * Author: Jalil Haidari
     **/
    public String saveAttachment(MultipartFile file, String uploadDirectory, String id, String folderName) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String fileName = null;
        if (file != null) {
            fileName = formatter.format(Calendar.getInstance().getTime()) + "_" + folderName + "_"
                    + file.getOriginalFilename();
            String saveDirectory = uploadDirectory + File.separator + id + File.separator;
            File test = new File(saveDirectory);
            if (!test.exists()) {
                test.mkdirs();
            }
            try {
                File f = new File(saveDirectory + fileName);
                // create the file
                if (!f.exists()) {

                    f.createNewFile();
                }
                FileOutputStream fout = new FileOutputStream(f);
                fout.write(file.getBytes());
                fout.close();
            } catch (Exception e) {

            }

        }
        return fileName;

    }

}