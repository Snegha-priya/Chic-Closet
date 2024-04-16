package com.chiccloset.config;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class MultitenantSchemaManager {
	
	@PersistenceContext
	private EntityManager entityManager;

	public void changeSchema(String tenant) {
		Session session = entityManager.unwrap(Session.class);
		session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				TenantContext.setCurrentTenant(tenant);
				connection.setSchema(tenant);
			}
		});
	}

}
