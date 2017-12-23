package io.github.cepr0.dualdb.writer;

import io.github.cepr0.dualdb.first.model.User;
import io.github.cepr0.dualdb.first.repo.UserRepo;
import io.github.cepr0.dualdb.second.model.Order;
import io.github.cepr0.dualdb.second.repo.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

/**
 * @author Cepr0, 2017-12-22
 */
@Slf4j
@Service
public class WriteDataService {
	
	private final UserRepo userRepo;
	private final OrderRepo orderRepo;
	
	public WriteDataService(UserRepo userRepo, OrderRepo orderRepo) {
		this.userRepo = userRepo;
		this.orderRepo = orderRepo;
	}
	
	@Async
	@EventListener
	@Transactional
	public void writeData(WriteEvent event) throws InterruptedException {
		
		Boolean isRollback = (Boolean) event.getSource();
		
		log.info("!!! Start writing");
		
		List<User> userList = userRepo.save(asList(
				new User("user1"),
				new User("user2")
		));
		
		List<Order> orderList = orderRepo.save(asList(
				new Order(1),
				new Order(2)
		));
		
		log.info(">>> Wrote users: {}, orders: {}", userList.size(), orderList.size());
		log.info("??? Wainng finishing transaction...");
		
		TimeUnit.SECONDS.sleep(10);
		if (isRollback) {
			log.info("!!! Exception !!!");
			throw new RuntimeException("Something wrong happen...");
		}
	}
}
