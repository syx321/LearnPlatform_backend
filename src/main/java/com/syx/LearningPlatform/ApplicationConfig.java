package com.syx.LearningPlatform;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

//@Configuration
//@EnableJpaRepositories
//@EntityScan("com.syx.LearningPlatform.model")
//@EntityScan("com.syx.LearningPlatform.repository")
public class ApplicationConfig {
    // 配置其他组件和属性
   // @Bean(name = "entityManagerFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//        return sessionFactory;
//    }
}