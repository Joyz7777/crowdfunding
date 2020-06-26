package com.jo.crowd.service.impl;

import com.jo.crowd.entity.Auth;
import com.jo.crowd.mapper.AuthMapper;
import com.jo.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    AuthMapper authMapper;

    @Override
    public List<Auth> getAuths() {

        return authMapper.selectByExample(null);
    }
}
