package io.github.cepr0.dualdb.writer;

import io.github.cepr0.dualdb.first.model.User;
import io.github.cepr0.dualdb.first.repo.UserRepo;
import io.github.cepr0.dualdb.second.model.Order;
import io.github.cepr0.dualdb.second.repo.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

/**
 * @author Cepr0, 2017-12-23
 */
@Slf4j
@Service
public class WriteDataServiceImpl implements WriteDataService {

	private final UserRepo userRepo;
	private final OrderRepo orderRepo;

	public WriteDataServiceImpl(UserRepo userRepo, OrderRepo orderRepo) {
		this.userRepo = userRepo;
		this.orderRepo = orderRepo;
	}

	@Transactional(transactionManager = "bitronixTransactionManager")
	@Override
	public void writeData(boolean isRollback) throws InterruptedException {

		log.info("### Start the writing transaction.");

		long usersCount = userRepo.count();
		long ordersCount = orderRepo.count();

		List<User> userList = userRepo.save(asList(
				new User("user1"),
				new User("user2")
		));
		userRepo.flush();

		List<Order> orderList = orderRepo.save(asList(
				new Order(1),
				new Order(2)
		));
		orderRepo.flush();

		log.info(">>> Wrote users: {}, orders: {}", userList.size() + usersCount, orderList.size() + ordersCount);
		log.info("??? Waiting finishing transaction...");

		TimeUnit.SECONDS.sleep(3);
		if (isRollback) {
			log.info("!!! Rolling back transaction...");
			throw new RuntimeException("Something wrong happened...");
		}
		log.info("### End of transaction.");
	}
}
