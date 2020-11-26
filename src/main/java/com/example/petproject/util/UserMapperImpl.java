package com.example.petproject.util;

import com.example.petproject.po.GenderType;
import com.example.petproject.po.LevelType;
import com.example.petproject.po.UserPo;
import com.example.petproject.vo.UserVo;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserVo userPoToVo(UserPo userpo) {
        if (userpo == null) {
            return new UserVo();
        }
        UserVo userVo = new UserVo();
        userVo.setAccount(userpo.getAccount());
        userVo.setEmail(userpo.getEmail());
        userVo.setGender(GenderType.getGender(userpo.getGenderId()));
        userVo.setLevel(LevelType.getType(userpo.getLevel()));
        userVo.setName(userpo.getName());
        userVo.setCreateDate(userpo.getCreateDate());
        return userVo;
    }
}
