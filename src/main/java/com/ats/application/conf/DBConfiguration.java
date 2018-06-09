package com.ats.application.conf;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBConfiguration {

	@Value("${jdbc.url}")
	private String strJNDIURL;
	@Value("${entitymanager.packagesToScan}")
	private String strScanPackage;
	@Value("${hibernate.dialect}")
	private String strDialect;
	@Value("${hibernate.show_sql}")
	private String strShowSQL;
	/*@Value("${db.url}")
	private String URL;*/
	
	/*@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(URL);
		return dataSource;
	}*/
	
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws NamingException {
		LocalSessionFactoryBean sessionFactoryBean = null;
		sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(getDSFromJNDI());
		sessionFactoryBean.setPackagesToScan(strScanPackage);
		Properties hbProperties = new Properties();
		hbProperties.put("hibernate.dialect", strDialect);
		hbProperties.put("hibernate.show_sql", strShowSQL);
		sessionFactoryBean.setHibernateProperties(hbProperties);
		return sessionFactoryBean;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean = null;
		HibernateJpaVendorAdapter jpaVendorAdapter = null;
		containerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		containerEntityManagerFactoryBean.setDataSource(getDSFromJNDI());
		containerEntityManagerFactoryBean.setPackagesToScan(strScanPackage);
		jpaVendorAdapter = new HibernateJpaVendorAdapter();
		Properties hbProperties = new Properties();
		hbProperties.put("hibernate.dialect", strDialect);
		hbProperties.put("hibernate.show_sql", strShowSQL);
		containerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		containerEntityManagerFactoryBean.setJpaProperties(hbProperties);
		return containerEntityManagerFactoryBean;
	}
	
	@Bean
	public JpaTransactionManager transactionManager() throws NamingException {
		JpaTransactionManager transactionManager = null;
		transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	private DataSource getDSFromJNDI() throws NamingException {
		return (DataSource) new JndiTemplate().lookup(strJNDIURL);
	}
	
}
