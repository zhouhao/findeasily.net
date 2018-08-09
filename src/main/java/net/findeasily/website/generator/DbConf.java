package net.findeasily.website.generator;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:application-dev.properties"})
public interface DbConf extends Config {
    @Key("spring.datasource.driverClassName")
    String driverName();

    @Key("spring.datasource.url")
    String dsUrl();

    @Key("spring.datasource.username")
    String username();

    @Key("spring.datasource.password")
    String password();
}
