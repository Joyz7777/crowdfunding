package com.jo.crowd.service.api;

import com.jo.crowd.entity.Menu;

import java.util.List;

public interface MenuService {


     List<Menu> getAll();

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);

    void removeMenuById(Integer id);
}
