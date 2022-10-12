package com.example.jingsai.db;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.SQLException;

public class MyBatisPlusGenerator {

    public static void main(String[] args) throws SQLException {
        FastAutoGenerator.create("jdbc:mysql://192.168.50.190:3306/jingsai", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("hejie") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:/Users/admin/Desktop/desktop/file"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.jingsai") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .entity("model")
                            .xml("xml");
                })
                .strategyConfig(builder -> {
                    builder.addInclude("process_info", "process_net"); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                    builder.mapperBuilder()
                            .enableBaseColumnList()
                            .enableBaseResultMap();
                    builder.entityBuilder().formatFileName("%s");//.enableLombok();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
