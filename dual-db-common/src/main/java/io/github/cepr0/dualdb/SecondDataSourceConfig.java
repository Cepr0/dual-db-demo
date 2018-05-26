package io.github.cepr0.dualdb;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Cepr0, 2017-12-21
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(value = "io.github.cepr0.dualdb.second.repo",
		entityManagerFactoryRef = "secondEntityManagerFactory",
		transactionManagerRef = "bitronixTransactionManager")
public class SecondDataSourceConfig {

	private final Environment env;
	private final DataSourceProvider dataSourceProvider;

	public SecondDataSourceConfig(Environment env, DataSourceProvider dataSourceProvider) {
		this.env = env;
		this.dataSourceProvider = dataSourceProvider;
	}

	@Bean(name = "secondDataSource")
	public DataSource dataSource() {
		return dataSourceProvider.get("second", "READ_UNCOMMITTED");
	}

	@Bean(name = "secondEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("secondDataSource") DataSource dataSource) {
		return CommonConfig.buildEntityManager(builder, dataSource, env, "second");
	}
}
