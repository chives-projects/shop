package com.shop.shopweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        //实体管理工厂引用名称，对应到@Bean注解对应的方法
        entityManagerFactoryRef = "entityManageFactoryPrimary",
        //事务管理工厂引用名称，对应到@Bean注解对应的方法
        transactionManagerRef = "transactionManagerPrimary",
        //用于配置扫描Repositories所在的package及子package，多个package时，分隔，eg:{"A","B"}。
        basePackages = {"com.shop.shopweb.repo"}
)
public class EntityManageConfig {

    @Autowired
    @Qualifier("myDataSource")
    private DataSource myDataSource;

    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        return entityManageFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManageFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManageFactory(EntityManagerFactoryBuilder builder){
        LocalContainerEntityManagerFactoryBean entityManagerFactory =  builder.dataSource(myDataSource)
                .packages("com.shop.shopweb.po").build();
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        jpaProperties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
//        jpaProperties.put("hibernate.connection.charSet", "utf-8");
//        jpaProperties.put("hibernate.show_sql", "false");
//        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }

    @Primary
    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManageFactory(builder).getObject());
    }

}
