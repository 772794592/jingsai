package com.example.jingsai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan({"com.example.jingsai.mapper", "com.example.jingsai.*.dao"})
@EnableTransactionManagement
@SpringBootApplication
//@MapperScan(basePackages= "mybatis/config.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
