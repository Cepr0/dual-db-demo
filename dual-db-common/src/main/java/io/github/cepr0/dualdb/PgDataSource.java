package io.github.cepr0.dualdb;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author Cepr0, 2018-05-26
 */
@Profile("postgres")
@Component
public class PgDataSource implements DataSourceProvider {
	@Override
	public DataSource get(String sourceName, String isolationLevel) {
		return build(
				sourceName,
				isolationLevel,
				"org.postgresql.xa.PGXADataSource",
				"postgres",
				"postgres",
				"jdbc:postgresql://localhost:5432/" + sourceName
		);
	}
}
