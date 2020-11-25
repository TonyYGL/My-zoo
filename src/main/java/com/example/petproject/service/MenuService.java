package com.example.petproject.service;

import com.example.petproject.po.MenuDetailPo;
import com.example.petproject.po.MenuPo;
import com.example.petproject.repository.MenuRepository;
import com.example.petproject.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    /**
     * @param status 1: 開啟 2: 關閉
     * @param level 1: 管理員 2:普通會員
     * @return 顯示於前端的menu vo
     */
    public List<MenuVo> getMenu(int status, int level) {

        List<MenuPo> menuPoList = menuRepository.findAll();
        menuPoList = menuPoList.stream().filter(menuPo -> menuPo.getStatus() == status
                && menuPo.getLevel() == level)
                .collect(toList());

        List<MenuVo> menuVoList = new ArrayList<>();
        for (MenuPo menuPo: menuPoList) {
            Map<String, String> functionMap = menuPo.getMenuDetailPos()
                    .stream()
                    .filter(menuDetailPo -> menuDetailPo.getStatus() == status
                            && menuDetailPo.getLevel() == level)
                    .collect(toMap(MenuDetailPo::getName, MenuDetailPo::getUrl));
            MenuVo menuVo = new MenuVo(menuPo.getName(), functionMap);
            menuVoList.add(menuVo);
        }
        return menuVoList;
    }

}
