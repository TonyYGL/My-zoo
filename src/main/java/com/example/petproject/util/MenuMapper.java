package com.example.petproject.util;

import com.example.petproject.po.MenuPo;
import com.example.petproject.vo.MenuVo;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {
    public MenuVo menuPoToVo(MenuPo menuPo) {
        return new MenuVo(menuPo.getName(), menuPo.getIcon(), menuPo.getUrl());
    }
}
