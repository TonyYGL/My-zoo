package com.example.petproject.util;

import com.example.petproject.po.UserPo;
import com.example.petproject.vo.UserVo;

import java.util.Optional;

public interface UserMapper {
    public Optional<UserVo> userPoToVo(UserPo userpo);
}
