package io.github.cepr0.dualdb;

import bitronix.tm.TransactionManagerServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * @author Cepr0, 2017-12-22
 */
@Configuration
public class BitronixConfig {

	@Bean
	public PlatformTransactionManager bitronixTransactionManager() {

		JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
		jtaTransactionManager.setTransactionManager(TransactionManagerServices.getTransactionManager());
		jtaTransactionManager.setUserTransaction(TransactionManagerServices.getTransactionManager());
		jtaTransactionManager.setAllowCustomIsolationLevels(true);
		jtaTransactionManager.setNestedTransactionAllowed(true);
		jtaTransactionManager.afterPropertiesSet();
		return jtaTransactionManager;
	}
}
