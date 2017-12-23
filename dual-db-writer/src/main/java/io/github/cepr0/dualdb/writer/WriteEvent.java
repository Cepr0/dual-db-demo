package io.github.cepr0.dualdb.writer;

import org.springframework.context.ApplicationEvent;

/**
 * @author Cepr0, 2017-12-22
 */
public class WriteEvent extends ApplicationEvent {
	public WriteEvent(Object source) {
		super(source);
	}
}
