package com.example.jingsai.test.service.imlp;

import com.example.jingsai.test.dao.UserDao;
import com.example.jingsai.test.pojo.User;
import com.example.jingsai.test.service.TestService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    private UserDao userDao;


    @Override
    public List<User> queryUser() {
        return userDao.queryAll();
    }
}
