package com.example.petproject.service;

import com.example.petproject.po.ImagePo;
import com.example.petproject.repository.ImageRepository;
import com.example.petproject.util.ImageMapper;
import com.example.petproject.util.UuidUtil;
import com.example.petproject.vo.ImageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageFileService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageMapper imageMapper;

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
    public String saveImage(MultipartFile[] uploadfiles) {
        for (MultipartFile uploadFile : uploadfiles) {
            if (!uploadFile.isEmpty()) {
                String originalName = uploadFile.getOriginalFilename();
                System.out.println(">>" + originalName);
                String fileType = originalName.substring(originalName.lastIndexOf("."));
                String newFileName = insertImageRecord(1, originalName);

                File file = new File(IMAGE_PATH + newFileName + fileType);
                try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                    fileOutputStream.write(uploadFile.getBytes());
                    fileOutputStream.flush();
                } catch (Exception e) {
                    file.delete();
                    e.printStackTrace();
                    logger.error("upload " + uploadFile.getOriginalFilename() + " failed, " + e.getMessage());
                    return UPLOAD_FAIL;
                }
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

    public List<ImageVo> findImageByUserId(long userId) {
        List<ImagePo> imagePoList = imageRepository.findAllByUserId(userId);
        List<ImageVo> imageVoList = new ArrayList<>();
        for (ImagePo imagePo : imagePoList) {
            imageVoList.add(imageMapper.poToVo(imagePo));
        }
        return imageVoList;
    }
}
