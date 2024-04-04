package com.chiccloset.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@SuppressWarnings("rawtypes")
@EnableTransactionManagement
public class MultitenantConfiguration {

	@Autowired
	DatasourceConfigUtil datasourceConfigUtil;

	private static final String defaultTenant = "public";
	private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

	private final Map<Object, Object> resolvedDataSources = new ConcurrentHashMap<>();

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		System.out.println("in setting tenant");
		AbstractRoutingDataSource dataSource = new MultitenantDataSource();
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(datasourceConfigUtil.getDriverClassName());
		dataSourceBuilder.url(datasourceConfigUtil.getDefaulturl());
		dataSourceBuilder.username(datasourceConfigUtil.getDbusername());
		dataSourceBuilder.password(datasourceConfigUtil.getPassword());
		resolvedDataSources.put(defaultTenant, dataSourceBuilder.build());

		dataSource.setDefaultTargetDataSource(resolvedDataSources.get(defaultTenant));
		dataSource.setTargetDataSources(resolvedDataSources);
		dataSource.afterPropertiesSet();
		return dataSource;
	}

	/*
	 * public AbstractRoutingDataSource dataSource() { multiTenantDataSource = new
	 * AbstractRoutingDataSource() {
	 * 
	 * @Override protected Object determineCurrentLookupKey() { return
	 * CURRENT_TENANT.get(); } };
	 * multiTenantDataSource.setTargetDataSources(resolvedDataSources);
	 * multiTenantDataSource.setDefaultTargetDataSource(defaultDataSource());
	 * multiTenantDataSource.afterPropertiesSet(); return multiTenantDataSource; }
	 */

	/*
	 * private DataSourceBuilder defaultDataSource() {
	 * System.out.println("in default source"); DataSourceBuilder dataSourceBuilder
	 * = DataSourceBuilder.create();
	 * dataSourceBuilder.driverClassName(datasourceConfigUtil.getDriverClassName());
	 * dataSourceBuilder.url(datasourceConfigUtil.getUrl());
	 * dataSourceBuilder.username(datasourceConfigUtil.getDbusername());
	 * dataSourceBuilder.password(datasourceConfigUtil.getPassword()); return
	 * dataSourceBuilder; }
	 */

	public void setCurrentTenant(String tenantId) {
		CURRENT_TENANT.set(tenantId);

	}

	public void addTenant(String tenantId) throws SQLException {
		System.out.println("in tenant");
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(datasourceConfigUtil.getDriverClassName());
		dataSourceBuilder.url(datasourceConfigUtil.getUrl()+tenantId);
		dataSourceBuilder.username(datasourceConfigUtil.getDbusername());
		dataSourceBuilder.password(datasourceConfigUtil.getPassword());
		try (Connection c = dataSourceBuilder.build().getConnection()) {
			AbstractRoutingDataSource dataSource = new MultitenantDataSource();
			resolvedDataSources.put(tenantId, dataSourceBuilder.build());
			dataSource.setTargetDataSources(resolvedDataSources);
			dataSource.afterPropertiesSet();
			System.out.println("Connection success");
		}
	}

	/*
	 * @Bean(name = "transactionManager") public PlatformTransactionManager
	 * transactionManager() { JpaTransactionManager transactionManager = new
	 * JpaTransactionManager();
	 * transactionManager.setEntityManagerFactory(entityManagerFactoryBean().
	 * getObject()); return transactionManager; }
	 * 
	 * @Bean(name = "entityManager") public LocalContainerEntityManagerFactoryBean
	 * entityManagerFactoryBean() { LocalContainerEntityManagerFactoryBean lCEMFB =
	 * new LocalContainerEntityManagerFactoryBean();
	 * lCEMFB.setDataSource(dataSource());
	 * lCEMFB.setPackagesToScan("com.varuvaai.contactus.entitymodel");
	 * HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	 * lCEMFB.setJpaVendorAdapter(vendorAdapter);
	 * lCEMFB.setPersistenceUnitName("varuvaai"); //
	 * lCEMFB.setJpaProperties(hibernateProperties()); return lCEMFB; }
	 * 
	 * private Properties hibernateProperties() { Properties properties = new
	 * Properties(); properties.put("hibernate.show_sql", true);
	 * properties.put("hibernate.format_sql", true);
	 * properties.put("hibernate.naming-strategy",
	 * "org.hibernate.cfg.ImprovedNamingStrategy"); return properties; }
	 */
}
