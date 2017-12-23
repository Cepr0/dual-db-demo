package io.github.cepr0.dualdb.reader;

import io.github.cepr0.dualdb.second.model.Order;
import io.github.cepr0.dualdb.second.repo.OrderRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

/**
 * @author Sergei Poznanski, 2017-12-23
 */
@Service
public class OrderService {

	private final OrderRepo orderRepo;

	public OrderService(OrderRepo orderRepo) {
		this.orderRepo = orderRepo;
	}

	@Transactional(readOnly = true, isolation = READ_UNCOMMITTED)
	public List<Order> findAll() {
		return orderRepo.findAll();
	}
}
