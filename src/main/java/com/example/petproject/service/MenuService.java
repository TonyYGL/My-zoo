package com.example.petproject.service;

import com.example.petproject.po.MenuPo;
import com.example.petproject.repository.MenuRepository;
import com.example.petproject.util.MenuMapper;
import com.example.petproject.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuMapper menuMapper;

    public void initMenu() {
        MenuPo adopt = new MenuPo();
        adopt.setName("領養專區");
        adopt.setIcon("pets");
        adopt.setUrl("/adopt");

        MenuPo shelter = new MenuPo();
        shelter.setName("收容之家");
        shelter.setIcon("house");
        shelter.setUrl("/shelter");

        MenuPo album = new MenuPo();
        album.setName("寵物相簿");
        album.setIcon("collections");
        album.setUrl("/album");

        List menus = Arrays.asList(adopt, shelter, album);
        menuRepository.saveAll(menus);
    }

    public List<MenuVo> findByStatusAndLevel(int status, int level) {
        return menuRepository.findByStatus(status)
                .stream()
                .filter(menuPo -> level <= menuPo.getLevel())
                .map(menuPo -> menuMapper.menuPoToVo(menuPo))
                .collect(Collectors.toList());
    }

}
