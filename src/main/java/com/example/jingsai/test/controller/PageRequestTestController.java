package com.example.jingsai.test.controller;

import com.example.jingsai.test.pojo.User;
import com.example.jingsai.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jingsai/")
public class PageRequestTestController {
    @Autowired
    private TestService service;

    @GetMapping("query")
    public List<User> query() {
        List<User> users = service.queryUser();
        System.out.print(users);
        return users;
    }
}
