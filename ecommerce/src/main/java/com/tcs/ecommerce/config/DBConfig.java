package com.tcs.ecommerce.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.tcs.ecommerce.repository")
@PropertySource("classpath:db.properties") // if file is in a location use
@ComponentScan("com.tcs.ecommerce")
public class DBConfig {

	@Autowired
	private Environment environment;
	//It will create a singleton object
	@Bean //@Bean("mysqlDataSource")
	//@Lazyformat_sql
	public DataSource getMySQLDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName(environment.getProperty("db.driver"));
		dataSource.setUrl(environment.getProperty("db.url"));
		dataSource.setUsername(environment.getProperty("db.username"));
		dataSource.setPassword(environment.getProperty("db.password"));
		
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory( ) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(getMySQLDataSource());
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		properties.setProperty("hibernamte.format_sql",environment.getProperty("hibernate.format_sql"));
		properties.setProperty("hibernamte.hbm2ddl.auto",environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernamte.dialect",environment.getProperty("hibernate.dialect"));
		
		entityManagerFactoryBean.setPackagesToScan("com.tcs.ecommerce.model");
		entityManagerFactoryBean.setJpaProperties(properties);
		
		return entityManagerFactoryBean;
		
	}
}
