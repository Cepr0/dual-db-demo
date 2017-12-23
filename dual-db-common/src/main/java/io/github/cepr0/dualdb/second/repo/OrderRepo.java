package io.github.cepr0.dualdb.second.repo;

import io.github.cepr0.dualdb.second.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Cepr0, 2017-12-21
 */
public interface OrderRepo extends JpaRepository<Order, Long> {
}
