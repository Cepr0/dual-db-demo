package io.github.cepr0.dualdb.reader;

import io.github.cepr0.dualdb.first.model.User;
import io.github.cepr0.dualdb.second.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.transaction.annotation.Propagation.NEVER;

/**
 * @author Cepr0, 2017-12-22
 */
@Slf4j
@Component
public class DataReader implements ApplicationRunner {
	
	private final UserService userService;
	private final OrderService orderService;
	
	public DataReader(UserService userService, OrderService orderService) {
		this.userService = userService;
		this.orderService = orderService;
	}
	
	@Async
	public void run(ApplicationArguments args) throws InterruptedException {
		//noinspection InfiniteLoopStatement
		while (true) {
			readDB();
		}
	}

	@Transactional(propagation = NEVER)
	public void readDB() throws InterruptedException {

		List<User> userList = userService.findAll();
		List<Order> orderList = orderService.findAll();
		log.info("<<< Read users: {}, orders: {}", userList.size(), orderList.size());

		TimeUnit.SECONDS.sleep(2);
	}
}
