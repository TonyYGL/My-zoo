package com.example.petproject.util;

import com.example.petproject.po.UserPo;
import com.example.petproject.vo.UserVo;

public interface UserMapper {
    public UserVo userPoToVo(UserPo userpo);
}
