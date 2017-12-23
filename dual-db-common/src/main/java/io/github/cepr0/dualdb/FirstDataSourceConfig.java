package io.github.cepr0.dualdb;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(value = "io.github.cepr0.dualdb.first.repo",
		entityManagerFactoryRef = "firstEntityManagerFactory",
		transactionManagerRef = "bitronixTransactionManager")
public class FirstDataSourceConfig {

	private final Environment env;

	public FirstDataSourceConfig(Environment env) {
		this.env = env;
	}

	@Primary
	@Bean(name = "firstDataSource")
	@ConfigurationProperties(prefix="datasource.first")
	public DataSource dataSource() {
		return CommonConfig.buildDataSource("first");
	}

	@Primary
	@Bean(name = "firstEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("firstDataSource") DataSource dataSource) {
		return CommonConfig.buildEntityManager(builder, dataSource, env, "first");
	}
}
