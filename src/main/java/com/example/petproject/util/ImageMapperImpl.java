package com.example.petproject.util;

import com.example.petproject.po.ImagePo;
import com.example.petproject.vo.ImageVo;
import org.springframework.stereotype.Component;

@Component
public class ImageMapperImpl implements ImageMapper {
    @Override
    public ImageVo poToVo(ImagePo imagePo) {
        if (imagePo == null) {
            throw new RuntimeException("Image po to vo : po is null");
        }
        ImageVo imageVo = new ImageVo();
        imageVo.setCreateTime(imagePo.getCreateTime().toString());
        String originalName = imagePo.getFileName();
        String fileType = originalName.substring(originalName.lastIndexOf("."));
        imageVo.setFileName(imagePo.getUuid() + fileType);
        return imageVo;
    }
}
