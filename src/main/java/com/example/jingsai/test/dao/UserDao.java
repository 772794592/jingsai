package com.example.jingsai.test.dao;

import com.example.jingsai.test.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM t_user")
    List<User> queryAll();
}
