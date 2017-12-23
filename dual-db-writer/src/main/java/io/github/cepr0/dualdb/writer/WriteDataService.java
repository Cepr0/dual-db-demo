package io.github.cepr0.dualdb.writer;

/**
 * @author Cepr0, 2017-12-23
 */
public interface WriteDataService {
	void writeData(boolean isRollback) throws InterruptedException;
}
