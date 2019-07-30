package com.cplh.springboot.mybatis.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MBGenerator {

    public static void main(String[] args) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://localhost:3306/mbg-dev?useUnicode=true&characterEncoding=UTF8";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(false)
                .setInclude("t_user") // 设置需要生成的表
               // .setInclude("b2c_crm_user") // 设置需要生成的表
                .setNaming(NamingStrategy.underline_to_camel);
        			
        config.setActiveRecord(false)
                .setEnableCache(false)
                .setAuthor("zhanglifeng")
                // 这里就直接输出到项目里面，不用再复制进来
                .setOutputDir("springboot-mybatis\\src\\main\\java")
                .setFileOverride(true)
                .setServiceName("%sService");
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent("com.cplh.springboot.mybatis")
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }
 }
