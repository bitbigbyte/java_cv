package com.xiaoke;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.ArrayList;

public class Generator {
    public static void main(String[] args) {
        //代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        //数据源配置
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/reggie");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        autoGenerator.setDataSource(dataSource);

        //全局配置
        GlobalConfig  globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        globalConfig.setAuthor("xiaoke");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false);
        globalConfig.setServiceName("%sService");
//        globalConfig.setMapperName("%sMapper");
//        globalConfig.setIdType(IdType.ASSIGN_ID);
        autoGenerator.setGlobalConfig(globalConfig);

        //包配置
        PackageConfig packageInfo = new PackageConfig();
        packageInfo.setParent("com.xiaoke");
//        packageInfo.setEntity("entity");
//        packageInfo.setMapper("mapper");
        autoGenerator.setPackageInfo(packageInfo);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setInclude(("employee,category,dish,setmeal").split(","));
//        strategyConfig.setInclude("tbl_user");
//        strategyConfig.setTablePrefix("tbl_");//去掉表前缀
//        strategyConfig.setVersionFieldName("version");//乐观锁
//        strategyConfig.setLogicDeleteFieldName("deleted");//逻辑删除
        TableFill createUser=new TableFill("create_user", FieldFill.INSERT);
        TableFill updateUser=new TableFill("update_user", FieldFill.INSERT_UPDATE);
        TableFill createTime=new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime=new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createUser);
        tableFills.add(updateUser);
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategyConfig.setTableFillList(tableFills);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}