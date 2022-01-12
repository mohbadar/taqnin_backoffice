package com.nsia.officems._util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class FileConverterUtil {

    /**
     * Turns file into byte[]
     *
     * @param file file to be converted to byte[]
     */
    public byte[] fileToByteArray(File file) throws IOException {
        // init array with file length
        byte[] byteArrayOfFile = new byte[(int) file.length()];

        FileInputStream fis = new FileInputStream(file);
        fis.read(byteArrayOfFile); // read file into bytes[]
        fis.close();

        return byteArrayOfFile;
    }
}