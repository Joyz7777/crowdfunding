package com.jo;


import com.jo.crowd.entity.Admin;
import com.jo.crowd.entity.Role;
import com.jo.crowd.mapper.AdminMapper;
import com.jo.crowd.mapper.RoleMapper;
import com.jo.crowd.mvc.controller.TestController;
import com.jo.crowd.service.api.AdminService;
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


}
