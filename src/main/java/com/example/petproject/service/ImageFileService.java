package com.example.petproject.service;

import com.example.petproject.po.ImagePo;
import com.example.petproject.repository.ImageRepository;
import com.example.petproject.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ImageFileService {

    @Autowired
    private ImageRepository imageRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String IMAGE_PATH = "C:/myzoo/images/upload/";
    private static File uploadPath;
    private static final String UPLOAD_SUCCESS = "上傳成功";
    private static final String UPLOAD_FAIL = "上傳失敗";

    static {
        uploadPath = new File(IMAGE_PATH);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
    }

    @Transactional
    public String saveImage(MultipartFile uploadfile) {

        if (!uploadfile.isEmpty()) {
            String originalName = uploadfile.getOriginalFilename();
            String fileType = originalName.substring(originalName.lastIndexOf("."));
            String newFileName = insertImageRecord(1, originalName);

            File file = new File(IMAGE_PATH + newFileName + "." + fileType);
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(uploadfile.getBytes());
                fileOutputStream.flush();
            } catch (Exception e) {
                file.delete();
                e.printStackTrace();
                logger.error("upload " + uploadfile.getOriginalFilename() + " failed, " + e.getMessage());
                return UPLOAD_FAIL;
            }
        }
        return UPLOAD_SUCCESS;
    }

    private String insertImageRecord(long userId, String fileName) {
        ImagePo imagePo = new ImagePo();
        imagePo.setUserId(userId);
        String uuid = UuidUtil.getUuid();
        imagePo.setUuid(uuid);
        imagePo.setFileName(fileName);
        imagePo.setCreateTime(LocalDateTime.now());
        imageRepository.save(imagePo);
        return uuid;
    }
}
