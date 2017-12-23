package io.github.cepr0.dualdb.reader;

import io.github.cepr0.dualdb.first.model.User;
import io.github.cepr0.dualdb.first.repo.UserRepo;
import io.github.cepr0.dualdb.second.model.Order;
import io.github.cepr0.dualdb.second.repo.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Cepr0, 2017-12-22
 */
@Slf4j
@Component
public class DataReader implements ApplicationRunner {
	
	private final UserRepo userRepo;
	private final OrderRepo orderRepo;
	
	public DataReader(UserRepo userRepo, OrderRepo orderRepo) {
		this.userRepo = userRepo;
		this.orderRepo = orderRepo;
	}
	
	@Async
	public void run(ApplicationArguments args) throws InterruptedException {
		
		//noinspection InfiniteLoopStatement
		while (true) {
			readData();
		}
	}
	
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
	public void readData() throws InterruptedException {
		List<User> userList = userRepo.findAll();
		List<Order> orderList = orderRepo.findAll();
		log.info("<<< Read users: {}, orders: {}", userList.size(), orderList.size());
		
		TimeUnit.SECONDS.sleep(2);
	}
}
