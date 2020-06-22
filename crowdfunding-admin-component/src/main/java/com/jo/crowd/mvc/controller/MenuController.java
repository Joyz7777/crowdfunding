package com.jo.crowd.mvc.controller;


import com.fasterxml.jackson.databind.ser.std.RawSerializer;
import com.jo.crowd.entity.Menu;
import com.jo.crowd.service.api.MenuService;
import com.jo.crowd.util.ResultEntity;
import com.sun.org.apache.regexp.internal.RE;
import jdk.management.resource.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/menu")
@Controller
public class MenuController {

    @Autowired
    MenuService menuService;



    @ResponseBody
    @RequestMapping("/remove.json")
    public ResultEntity<String> removeMenu(@RequestParam("id") Integer id){
        menuService.removeMenuById(id);

        return ResultEntity.successWithNoData();

    }



    @ResponseBody
    @RequestMapping("/update.json")
    public ResultEntity<String> updateMenu(Menu menu){

        menuService.updateMenu(menu);

            return ResultEntity.successWithNoData();
        }


    @ResponseBody
    @RequestMapping("/save.json")
    public ResultEntity<String> saveMenu(Menu menu){

        menuService.saveMenu(menu);

        return  ResultEntity.successWithNoData();
    }



    @ResponseBody
    @RequestMapping("/get/list.json")
    public ResultEntity<Menu> getMenuList() {

        List<Menu> menuList = menuService.getAll();

        // 2.声明一个变量用来存储找到的根节点 Menu root = null;
        Menu root = null;
        // 3.创建 Map 对象用来存储 id 和 Menu 对象的对应关系便于查找父节点
        Map<Integer, Menu> menuMap = new HashMap<>();
        //   4.遍历 menuList 填充 menuMap
        for (Menu menu : menuList) {
            Integer id = menu.getId();
            menuMap.put(id, menu);
        }


//         5.再次遍历 menuList 查找根节点、组装父子节点
        for (Menu menu : menuList) {
//         6.获取当前 menu 对象的 pid 属性值
            Integer pid = menu.getPid();
//         7.如果 pid 为 null，判定为根节点
            if (pid == null) {
                root = menu;
//         8.如果当前节点是根节点，那么肯定没有父节点，不必继续执行
                continue;
            }
//         9.如果 pid 不为 null，说明当前节点有父节点，那么可以根据 pid 到 menuMap 中 查找对应的 Menu 对象
            Menu father = menuMap.get(pid);
//         10.将当前节点存入父节点的 children 集合
            father.getChildren().add(menu);
        }

        return  ResultEntity.successWithData(root);
//        return ResultEntity.successWithData(all);
    }
}
