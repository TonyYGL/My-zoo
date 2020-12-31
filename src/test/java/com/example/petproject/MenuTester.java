package com.example.petproject;

import com.example.petproject.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MenuTester {

    @Autowired
    MenuService menuService;

    @Test
    public void initMenu() {
        menuService.initMenu();
    }

    @Test
    public void findByStatusAndLevel() {
        int status = 1;
        int level = 1;
        assertEquals(4, menuService.findByStatusAndLevel(status, level).size());
    }
}
