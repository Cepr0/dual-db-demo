package io.github.cepr0.dualdb;

import bitronix.tm.resource.jdbc.PoolingDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Cepr0, 2018-05-26
 */
public interface DataSourceProvider {
	DataSource get(String sourceName, String isolationLevel);

	default PoolingDataSource build(
			String uniqueName,
			String isolationLevel,
			String className,
			String dbUser,
			String dbPassword,
			String url
	) {
		PoolingDataSource pds = new PoolingDataSource();
		pds.setUniqueName(uniqueName);
		pds.setClassName(className);
		pds.setMaxPoolSize(16);
		pds.setMinPoolSize(5);
		pds.setAllowLocalTransactions(true);
		pds.setIsolationLevel(isolationLevel);
		pds.setShareTransactionConnections(true);
		pds.setEnableJdbc4ConnectionTest(true);

		Properties driverProperties = pds.getDriverProperties();
		driverProperties.put("user", dbUser);
		driverProperties.put("password", dbPassword);
		driverProperties.put("url", url);

		pds.init();
		return pds;
	}
}
