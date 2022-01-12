package com.nsia.officems.gop.complaint.requests;

import org.springframework.http.codec.multipart.Part;

public class UploadComplaintDocumentRequest {

    private String fileType;

    private Part file;

    private String fileName;

}
