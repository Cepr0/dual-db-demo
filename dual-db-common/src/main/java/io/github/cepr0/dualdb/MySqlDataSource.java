package io.github.cepr0.dualdb;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author Cepr0, 2018-05-26
 */
@Profile("mysql")
@Component
public class MySqlDataSource implements DataSourceProvider {
	@Override
	public DataSource get(String sourceName, String isolationLevel) {
		return build(
				sourceName,
				isolationLevel,
				"com.mysql.jdbc.jdbc2.optional.MysqlXADataSource",
				"root",
				"root",
				"jdbc:mysql://localhost:3306/" + sourceName
		);
	}
}
