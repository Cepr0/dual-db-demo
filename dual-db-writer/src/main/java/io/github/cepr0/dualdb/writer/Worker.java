package io.github.cepr0.dualdb.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cepr0, 2017-12-22
 */
@Slf4j
@Service
public class Worker {
	
	private final WriteDataService service;

	public Worker(WriteDataService service) {
		this.service = service;
	}
	
	@Async
	@EventListener
	@Transactional
	public void writeData(WriteEvent event) throws InterruptedException {
		service.writeData((Boolean) event.getSource());
	}
}
