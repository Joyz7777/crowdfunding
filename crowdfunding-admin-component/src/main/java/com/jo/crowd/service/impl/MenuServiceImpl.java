package com.jo.crowd.service.impl;

import com.jo.crowd.entity.Menu;
import com.jo.crowd.mapper.MenuMapper;
import com.jo.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    MenuMapper mm;


    @Override
    public List<Menu> getAll() {


        return mm.selectByExample(null);
    }

    @Override
    public void saveMenu(Menu menu) {

        mm.insert(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        mm.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void removeMenuById(Integer id) {
        mm.deleteByPrimaryKey(id);
    }
}
