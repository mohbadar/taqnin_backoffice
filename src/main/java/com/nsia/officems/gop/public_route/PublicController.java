package com.nsia.officems.gop.public_route;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.ExceptionUtil;
import com.nsia.officems.gop.document_upload_profile.DocumentUpload;
import com.nsia.officems.gop.document_upload_profile.DocumentUploadService;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.proposal.Proposal;
import com.nsia.officems.gop.proposal.ProposalService;

import org.springframework.core.io.Resource;

import org.apache.commons.io.FileUtils;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping({ "/api/public" })
public class PublicController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${app.upload.profile}")
    private String uploadDir;
    @Value("${app.profile.document}")
    private String upoadDoc;
    @Value("${app.individuals.dir}")
    private String individualsDir;
    @Value("${app.upload.user}")
    private String userUploadDirectory;

    @Autowired
    private ExceptionUtil exceptionUtil;

    @Autowired
    private DocumentUploadService documentUploadService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private UserService userService;

    private String failureEmailSub = "500 - GOP Exception - Public API";

    @RequestMapping(value = "/profile/{Id}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getProfileImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("Id") Long Id, @RequestParam(required = false) String size) throws Exception {
        logger.info("Entry PublicController>getProfileImage() - GET");
        try {
            Profile profile = profileService.findByIdWithoutRelation(Id);
            String avaaterName = profile.getAvatar();
            String avatarPhotoPath = uploadDir + '/' + Id + "/profile/" + avaaterName;
            System.out.println("avatarPhotoPath" + avatarPhotoPath);
            int width = -1;
            int height = -1;
            if (size != null && size.length() > 0) {
                String[] sizeParts = size.split("x|X");
                width = Integer.parseInt(sizeParts[0]);
                height = Integer.parseInt(sizeParts[1]);
            }
            if (new File(avatarPhotoPath).exists()) {
                final ByteArrayOutputStream bos = new ByteArrayOutputStream();
                final BufferedImage bufferedImage = ImageIO.read(new FileInputStream(avatarPhotoPath));
                if (width != -1 && height != -1) {
                    final BufferedImage thumbnail = Scalr.resize(bufferedImage, Mode.AUTOMATIC, width, height);
                    ImageIO.write(thumbnail, "png", bos);
                } else {
                    ImageIO.write(bufferedImage, "png", bos);
                }
                return bos.toByteArray();
            }
            return null;
        } catch (Exception e) {
            System.out.println(">>>> the exception message:" + e.toString());
            exceptionUtil.notifyDevTeam(failureEmailSub, e, null, null, request, false);
            return null;
        }
    }

    @RequestMapping(value = "/profile/{Id}/document", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getProfileDocument(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("Id") Long Id, @RequestParam(required = false) String size) throws Exception {
        logger.info("Entry PublicController>getProfileImage() - GET");
        try {
            DocumentUpload documentUpload = documentUploadService.findById(Id);
            String avaaterName = documentUpload.getFileName();
            String avatarPhotoPath = upoadDoc + '/' + Id + "/profile/" + avaaterName;
            System.out.println("avatarPhotoPath" + avatarPhotoPath);
            int width = -1;
            int height = -1;
            if (size != null && size.length() > 0) {
                String[] sizeParts = size.split("x|X");
                width = Integer.parseInt(sizeParts[0]);
                height = Integer.parseInt(sizeParts[1]);
            }
            if (new File(avatarPhotoPath).exists()) {
                final ByteArrayOutputStream bos = new ByteArrayOutputStream();
                final BufferedImage bufferedImage = ImageIO.read(new FileInputStream(avatarPhotoPath));
                if (width != -1 && height != -1) {
                    final BufferedImage thumbnail = Scalr.resize(bufferedImage, Mode.AUTOMATIC, width, height);
                    ImageIO.write(thumbnail, "png", bos);
                } else {
                    ImageIO.write(bufferedImage, "png", bos);
                }
                return bos.toByteArray();
            }
            return null;
        } catch (Exception e) {
            System.out.println(">>>> the exception message:" + e.toString());
            exceptionUtil.notifyDevTeam(failureEmailSub, e, null, null, request, false);
            return null;
        }
    }

    @RequestMapping(value = "/proposal/{Id}/{proposal_number}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getProposalImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("Id") Long Id, @PathVariable("proposal_number") Integer proposal_number,
            @RequestParam(required = false) String size) throws Exception {
        logger.info("Entry PublicController>getProfileImage() - GET");
        try {
            Proposal proposal = proposalService.findById(Id);
            String avaaterName = proposal.getAttachment();
            String saveDirectory = individualsDir + "/" + proposal_number + "/" + avaaterName;
            int width = -1;
            int height = -1;
            if (size != null && size.length() > 0) {
                String[] sizeParts = size.split("x|X");
                width = Integer.parseInt(sizeParts[0]);
                height = Integer.parseInt(sizeParts[1]);
            }
            if (new File(saveDirectory).exists()) {
                final ByteArrayOutputStream bos = new ByteArrayOutputStream();
                final BufferedImage bufferedImage = ImageIO.read(new FileInputStream(saveDirectory));
                if (width != -1 && height != -1) {
                    final BufferedImage thumbnail = Scalr.resize(bufferedImage, Mode.AUTOMATIC, width, height);
                    ImageIO.write(thumbnail, "png", bos);
                } else {
                    ImageIO.write(bufferedImage, "png", bos);
                }
                return bos.toByteArray();
            }
            return null;
        } catch (Exception e) {
            System.out.println(">>>> the exception message:" + e.toString());
            exceptionUtil.notifyDevTeam(failureEmailSub, e, null, null, request, false);
            return null;
        }
    }

    @RequestMapping(value = "/user/{Id}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getUserImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("Id") Long Id, @RequestParam(required = false) String size) throws Exception {
        logger.info("Entry PublicController>getProfileImage() - GET");
        try {
            User user = userService.findById(Id);
            String avatarName = user.getAvatar();
            String avatarPhotoPath = userUploadDirectory + '/' + Id + "/image/" + avatarName;
            System.out.println("avatarPhotoPath" + avatarPhotoPath);
            int width = -1;
            int height = -1;
            if (size != null && size.length() > 0) {
                String[] sizeParts = size.split("x|X");
                width = Integer.parseInt(sizeParts[0]);
                height = Integer.parseInt(sizeParts[1]);
            }
            if (new File(avatarPhotoPath).exists()) {
                final ByteArrayOutputStream bos = new ByteArrayOutputStream();
                final BufferedImage bufferedImage = ImageIO.read(new FileInputStream(avatarPhotoPath));
                if (width != -1 && height != -1) {
                    final BufferedImage thumbnail = Scalr.resize(bufferedImage, Mode.AUTOMATIC, width, height);
                    ImageIO.write(thumbnail, "png", bos);
                } else {
                    ImageIO.write(bufferedImage, "png", bos);
                }
                return bos.toByteArray();
            }
            return null;
        } catch (Exception e) {
            System.out.println(">>>> the exception message:" + e.toString());
            exceptionUtil.notifyDevTeam(failureEmailSub, e, null, null, request, false);
            return null;
        }
    }

}
