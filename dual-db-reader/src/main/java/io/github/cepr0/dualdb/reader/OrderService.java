package io.github.cepr0.dualdb.reader;

import io.github.cepr0.dualdb.second.model.Order;

import java.util.List;

/**
 * @author Cepr0, 2017-12-23
 */
public interface OrderService {
	List<Order> findAll();
}
