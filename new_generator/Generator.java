package com.xiaoke;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.sql.Types;
import java.util.Collections;

public class Generator {
    /**
     * 代码生成器
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        //配置数据源
        FastAutoGenerator.create("jdbc:mysql://localhost:20/chatroom?characterEncoding=utf-8&serverTimezone=Asia/Shanghai",
                        "root",
                        "123456")
                .globalConfig(builder -> {
                    builder.author("xiaoke")//设置作者
                            .dateType(DateType.ONLY_DATE)//时间策略
                            .commentDate("yyyy-MM-dd")//注释日期
                            .disableOpenDir()//不打开文件夹
                            //.enableSwagger()//启用swagger
                            //.fileOverride()//覆盖已生成文件
                            .outputDir(System.getProperty("user.dir") + "/src/main/java");//指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode=metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode== Types.SMALLINT){
                        //自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
                .packageConfig(builder -> {
                    builder.parent("com.xiaoke")//设置父包名
                            //.moduleName()//模块包名
                            .entity("entity")//实体类包名
                            .controller("controller")//控制层包名
                            .mapper("mapper")//mapper层包名
                            .xml("mapper.xml")//xml包名
                            .service("service")//service层包名
                            .serviceImpl("service.impl")//service实现类包名
                            //.other("dto")//自定义包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    System.getProperty("user.dir") + "/src/main/resources/mapper"));//自定义mapper.xml文件输出目录
                })
                .strategyConfig(builder -> {
                    builder.addInclude()//设置要生成的表名
                            //.addTablePrefix("sys_")//增加过滤表前缀
                            //.addTableSuffix()//增加过滤表后缀
                            //.addFieldPrefix()//增加过滤字段前缀
                            //.addFieldSuffix()//增加过滤字段后缀
                            //设置controller
                            .controllerBuilder().enableRestStyle()//启用rest风格
                            //设置entity
                            .entityBuilder().enableLombok()//开启lombok
                            .enableChainModel()//链式
                            .naming(NamingStrategy.underline_to_camel)//数据表映射实体命名策略
                            .columnNaming(NamingStrategy.underline_to_camel)//表字段映射实体属性命名策略
                            .addTableFills(new Column("create_user", FieldFill.INSERT))//自动填充
                            .addTableFills(new Column("update_user", FieldFill.INSERT_UPDATE))
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                            //.versionColumnName()//乐观锁数据库字段
                            //.versionPropertyName()//乐观锁实体类名称
                            //.idType(IdType.AUTO)//添加全局主键类型
                            //设置service
                            .serviceBuilder().formatServiceFileName("%sService")//格式化生成service文件，相当于把I过滤了
                            .formatServiceImplFileName("%sServiceImpl");
                            //设置mapper
                            //.mapperBuilder().enableMapperAnnotation();//开启mapper注解
                })
                .templateEngine(new FreemarkerTemplateEngine())//使用Freemarker引擎模板，默认是Velocity引擎模板
                .execute();
    }
}