package com.dawid.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@PropertySource("classpath:persistence-mysql.properties")
public class DBConfig {

    private final Environment env;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public DBConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public ComboPooledDataSource dataSource(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser(env.getProperty("jdbc.user"));
        comboPooledDataSource.setPassword(env.getProperty("jdbc.password"));
        comboPooledDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        try {
            comboPooledDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        comboPooledDataSource.setMaxIdleTime(getIntProperty("pool.maxidletime"));
        comboPooledDataSource.setMinPoolSize(getIntProperty("pool.minpoolsize"));
        comboPooledDataSource.setInitialPoolSize(getIntProperty("pool.idlesize"));
        comboPooledDataSource.setMaxPoolSize(getIntProperty("pool.maxpoolsize"));
        return comboPooledDataSource;
    }
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        //hibernateProperties.put("hibernate.hbm2ddl.auto", "create");
        hibernateProperties.put("hibernate.connection.characterEncoding","utf8");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        return localSessionFactoryBean;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory.getObject());
        return hibernateTransactionManager;
    }

    public int getIntProperty(String s){
        return Integer.parseInt(env.getProperty(s));
    }
}
