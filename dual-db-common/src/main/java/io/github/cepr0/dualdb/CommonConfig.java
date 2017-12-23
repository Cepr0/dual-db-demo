package io.github.cepr0.dualdb;

import bitronix.tm.resource.jdbc.PoolingDataSource;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author Cepr0, 2017-12-22
 */
@Configuration
public class CommonConfig {

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setThreadNamePrefix("asyncExecutor");
		executor.initialize();
		return executor;
	}

	public static LocalContainerEntityManagerFactoryBean buildEntityManager(EntityManagerFactoryBuilder builder, DataSource dataSource, Environment env, String name) {

		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));

		return builder
				.dataSource(dataSource)
				.packages("io.github.cepr0.dualdb." + name + ".model")
				.persistenceUnit(name)
				.properties(properties)
				.jta(true)
				.build();
	}

	public static DataSource buildDataSource(String name) {

		PoolingDataSource pds = new PoolingDataSource();
//		PoolingDataSource pds = new PoolingDataSourceBean();
		pds.setUniqueName(name);
		pds.setClassName("org.postgresql.xa.PGXADataSource");
		pds.setMaxPoolSize(10);
		pds.setAllowLocalTransactions(true);
		pds.getDriverProperties().put("user", "postgres");
		pds.getDriverProperties().put("password", "postgres");
		pds.getDriverProperties().put("url", "jdbc:postgresql://localhost:5432/" + name);
		//pds.getDriverProperties().put("max_prepared_transactions", 10);
		//pds.getDriverProperties().put("driverClassName", "org.postgresql.Driver");
		pds.init();
		return pds;

//		return DataSourceBuilder.create().build();
	}
}
