package io.github.cepr0.dualdb;

import bitronix.tm.resource.jdbc.PoolingDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Cepr0, 2017-12-23
 */
public class DataSourceConfig {
	public static DataSource buildDataSource(String name) {

		PoolingDataSource pds = new PoolingDataSource();
//		PoolingDataSource pds = new PoolingDataSourceBean();
		pds.setUniqueName(name);
		pds.setClassName("org.postgresql.xa.PGXADataSource");
		pds.setMaxPoolSize(16);
		pds.setMinPoolSize(5);
		pds.setAllowLocalTransactions(true);
		pds.setIsolationLevel("READ_UNCOMMITTED");
		pds.setShareTransactionConnections(true);
		pds.setEnableJdbc4ConnectionTest(true);

		Properties driverProperties = pds.getDriverProperties();
		driverProperties.put("user", "postgres");
		driverProperties.put("password", "postgres");
		driverProperties.put("url", "jdbc:postgresql://localhost:5432/" + name);

		pds.init();
		return pds;

//		return DataSourceBuilder.create().build();
	}

}
