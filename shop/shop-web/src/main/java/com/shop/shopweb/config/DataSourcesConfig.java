package com.shop.shopweb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @ClassNameDataSourcesConfig
 * @Description 数据源
 * @Author csc
 * @Date 2019/6/14 17:01
 * @Version V1.0
 **/
@Configuration
public class DataSourcesConfig {
    @Primary
    @Qualifier("myDataSource")
    @Bean(name = "myDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.csc")
    public DataSource ksyc(){
        return DataSourceBuilder.create().build();
    }

}
