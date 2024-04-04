package com.chiccloset.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:datasourceconfig.properties")
public class DatasourceConfigUtil {

	@Value("#{${driverclassname}}")
	private String driverClassName;

	public String getDriverClassName() {
		return driverClassName;
	}
	
	@Value("#{${defaulturl}}")
	private String defaulturl;

	public String getDefaulturl() {
		return defaulturl;
	}
	
	@Value("#{${url}}")
	private String url;

	public String getUrl() {
		return url;
	}

	@Value("#{${dbpassword}}")
	private String password;

	public String getPassword() {
		return password;
	}
	
	@Value("#{${dbusername}}")
	private String dbusername;

	public String getDbusername() {
		return dbusername;
	}
	
	@Value("#{${showsql}}")
	private Boolean showsql;

	public Boolean getShowsql() {
		return showsql;
	}
	
	@Value("#{${awsbaseurl}}")
	private String awsbaseurl;

	public String getAwsbaseurl() {
		return awsbaseurl;
	}
}