package io.github.cepr0.dualdb.writer;

import org.springframework.context.ApplicationEvent;

/**
 * @author Cepr0, 2017-12-22
 */
public class RepeatEvent extends ApplicationEvent {
	
	public RepeatEvent(Object source) {
		super(source);
	}
}
