package com.chiccloset.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<Object> {

	public static final String DEFAULT_SCHEMA = "public";
	
	@Override
	public String resolveCurrentTenantIdentifier() {
		if (TenantContext.getCurrentTenant() == null) {
			return DEFAULT_SCHEMA;
		} else {
			return TenantContext.getCurrentTenant();
		}
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
