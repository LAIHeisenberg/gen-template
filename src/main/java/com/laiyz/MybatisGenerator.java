package com.laiyz;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MybatisGenerator {

    private static final String jdbcUrl = "jdbc:sqlite:/home/laiyz/projects/DataKeeperAdmin/datakeeper-admin-web/src/main/resources/datakeeper-db.sqlite";
    private static final String username = "";
    private static final String password = "";
    private static final String outputJavaDir = "/home/laiyz/桌面/java";
    private static final String parentPackageName = "com.longmai.datakeeper.dao";
    private static final String outputMapperXmlDir = "/home/laiyz/桌面/xml";

    public static void main(String[] args){

        FastAutoGenerator.create(jdbcUrl, username, password)
                .globalConfig(builder -> {
                    builder.author("longmai") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outputJavaDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackageName) // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outputMapperXmlDir)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("dk_masking_column","dk_db_user_masking_column","dk_db_masking_user","dk_masking_algorithm") // 设置需要生成的表名
                            .addTablePrefix("dk_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }


}
