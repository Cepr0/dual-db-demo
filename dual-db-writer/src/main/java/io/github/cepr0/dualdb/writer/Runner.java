package io.github.cepr0.dualdb.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Cepr0, 2017-12-22
 */
@Slf4j
@Component
public class Runner {
	
	private final ApplicationEventPublisher publisher;

	public Runner(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
	
	@Async
	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		publisher.publishEvent(new RepeatEvent(false));
	}
	
	@Async
	@EventListener
	public void loop(RepeatEvent event) throws InterruptedException {

		Boolean isRollback = (Boolean) event.getSource();

		publisher.publishEvent(new WriteEvent(isRollback));
		log.info("<<<@>>> Write event has been sent...");

		TimeUnit.SECONDS.sleep(5);
		Random random = ThreadLocalRandom.current();
		publisher.publishEvent(new RepeatEvent(random.nextBoolean()));
	}
}
