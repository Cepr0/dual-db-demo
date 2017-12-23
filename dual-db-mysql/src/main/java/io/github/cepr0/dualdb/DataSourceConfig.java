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
		pds.setClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
		pds.setMaxPoolSize(16);
		pds.setMinPoolSize(5);
		pds.setAllowLocalTransactions(true);
		pds.setIsolationLevel("READ_UNCOMMITTED");
		pds.setShareTransactionConnections(true);
		pds.setEnableJdbc4ConnectionTest(true);

		Properties driverProperties = pds.getDriverProperties();
		driverProperties.put("user", "root");
		driverProperties.put("password", "root");
		driverProperties.put("url", "jdbc:mysql://localhost:3306/" + name);

		pds.init();
		return pds;

//		return DataSourceBuilder.create().build();
	}

}
