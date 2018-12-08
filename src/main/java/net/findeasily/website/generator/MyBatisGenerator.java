package net.findeasily.website.generator;

import java.util.HashMap;
import java.util.Map;

import org.aeonbits.owner.ConfigFactory;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.Lists;


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
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude("user");

        config.setActiveRecord(false)
                .setIdType(IdType.INPUT)
                .setAuthor("Hao Zhou")
                .setOutputDir("src/main/java")
                .setBaseResultMap(true)
                .setActiveRecord(false)
                .setEnableCache(false)
                .setFileOverride(true)
                .setServiceName("%sService");

        TemplateConfig tc = new TemplateConfig()
                .setXml(null);

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        cfg.setFileOutConfigList(Lists.newArrayList(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "src/main/resources/mybatis/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        }));

        PackageConfig pc = new PackageConfig()
                .setParent(packageName)
                .setEntity("domain")
                .setController("controller");

        new AutoGenerator()
                .setCfg(cfg)
                .setTemplate(tc)
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(pc)
                .execute();

    }
}
