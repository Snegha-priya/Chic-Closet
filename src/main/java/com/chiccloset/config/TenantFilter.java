package com.chiccloset.config;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
@Order(1)
class TenantFilter implements Filter {

	@Autowired
	private MultitenantConfiguration multitenantConfiguration;

	@Autowired
	private AbstractRoutingDataSource abstractRoutingDataSource;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String tenantName = req.getHeader("X-TenantID");
		System.out.println("tenantName :: " + tenantName);

		if (!abstractRoutingDataSource.getResolvedDataSources().containsKey(tenantName)) {
			System.out.println("Tenant not present");
			try {
				multitenantConfiguration.addTenant(tenantName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		multitenantConfiguration.setCurrentTenant(tenantName);
		TenantContext.setCurrentTenant(tenantName);
		System.out.println("current tenant set");
		try {
			chain.doFilter(request, response);
		} finally {
			TenantContext.setCurrentTenant("");
		}
	}
}
