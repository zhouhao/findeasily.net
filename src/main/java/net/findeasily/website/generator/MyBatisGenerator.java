package net.findeasily.website.generator;

import org.aeonbits.owner.ConfigFactory;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


public class MyBatisGenerator {

    public static void main(String[] args) {
        DbConf dbConf = ConfigFactory.create(DbConf.class);

        String packageName = "net.findeasily.website";
        GlobalConfig config = new GlobalConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbConf.dsUrl())
                .setUsername(dbConf.username())
                .setPassword(dbConf.password())
                .setDriverName(dbConf.driverName());

        StrategyConfig strategyConfig = new StrategyConfig();

        strategyConfig.setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude("user");

        config.setActiveRecord(false)
                .setIdType(IdType.UUID)
                .setAuthor("Hao Zhou")
                .setOutputDir("src/main/java")
                .setBaseResultMap(true)
                .setActiveRecord(false)
                .setEnableCache(false)
                .setFileOverride(true)
                .setServiceName("%sService");

        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(new PackageConfig().setParent(packageName).setEntity("domain"))
                .execute();

    }
}
