package com.example.petproject.util;

import com.example.petproject.po.MenuDetailPo;
import com.example.petproject.po.MenuPo;
import com.example.petproject.vo.MenuVo;

public interface MenuMapper {

    public MenuVo menuPoToVo(MenuPo menuPo, MenuDetailPo menuDetailPo);
}
