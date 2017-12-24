package io.github.cepr0.dualdb.reader;

import io.github.cepr0.dualdb.second.model.Order;
import io.github.cepr0.dualdb.second.repo.OrderRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

/**
 * @author Cepr0, 2017-12-23
 */
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepo orderRepo;

	public OrderServiceImpl(OrderRepo orderRepo) {
		this.orderRepo = orderRepo;
	}

	@Transactional(propagation = REQUIRES_NEW, isolation = READ_UNCOMMITTED,
			transactionManager = "bitronixTransactionManager", readOnly = true)
	public List<Order> findAll() {
		return orderRepo.findAll();
	}
}
