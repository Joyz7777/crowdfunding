package com.jo;


import com.jo.crowd.entity.Admin;
import com.jo.crowd.entity.Menu;
import com.jo.crowd.entity.Role;
import com.jo.crowd.mapper.AdminMapper;
import com.jo.crowd.mapper.RoleMapper;
import com.jo.crowd.mvc.controller.TestController;
import com.jo.crowd.service.api.AdminService;
import com.jo.crowd.service.api.MenuService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml", "classpath:spring-web-mvc.xml"})
public class test {


    public static void main(String[] args) {

    }

    private MockMvc mockMvc;


    @Autowired
    private TestController controller;

    @Autowired
    private DataSource dataSource;

    @Autowired
    AdminMapper mapper;


    @Autowired
    RoleMapper roleMapper;



    @Autowired
    AdminService service;


    @Autowired
    MenuService menuService;


    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void test() throws Exception {

        ResultActions perform = mockMvc.perform(get("test/ssm.html"));
        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);

    }


    @Test
    public void testLog() {


        Logger logger = LoggerFactory.getLogger(test.class);

        logger.info("info log");
        logger.debug("debug log");
        logger.warn("warm log");
        logger.error("error log");
    }

    @Test
    public void test1() throws Exception {

        Admin admin = mapper.selectByPrimaryKey(11);
        System.out.println(admin.getUserPswd());


    }

    @Test
    public void testTxn() {
        Admin byId = service.getById(1);

        Admin admin = new Admin();
        admin.setEmail("111@qq.com");
        admin.setLoginAcct("admin");
        admin.setUserName("jason");
        admin.setUserPswd("password");
        boolean b = service.saveAdmin(admin);
//        System.out.println(1/0);
        System.err.println(b);
    }


    @Test
    public void batchInsertRole(){
        for(int i = 0; i < 100 ; i ++){
            Role role = new Role(null, "Role" + i);
            roleMapper.insertSelective(role);
        }
    }


    @Test
    public void getAllMenu(){

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

        System.err.println(root);
        for (Integer integer : menuMap.keySet()) {
            System.out.println(menuMap.get(integer));
            System.out.println();
        }


    }


}
