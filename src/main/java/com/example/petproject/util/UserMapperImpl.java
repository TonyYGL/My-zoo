package com.example.petproject.util;

import com.example.petproject.po.GenderType;
import com.example.petproject.po.UserPo;
import com.example.petproject.vo.UserVo;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserVo userPoToVo(UserPo userpo) {
        if (userpo == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        userVo.setAccount(userpo.getAccount());
        userVo.setPassword(userpo.getPassword());
        userVo.setEmail(userpo.getEmail());
        userVo.setGender(GenderType.getGender(userpo.getGenderId()));
        String level = userpo.getLevel() == 1 ? "管理員" : "一般會員";
        userVo.setLevel(level);
        userVo.setName(userpo.getName());
        userVo.setCreateDate(userpo.getCreateDate());

        return userVo;
    }
}
