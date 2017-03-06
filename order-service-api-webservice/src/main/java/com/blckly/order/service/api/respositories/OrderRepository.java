package com.blckly.order.service.api.respositories;

import com.blckly.order.service.api.domain.Order;
import com.blckly.order.service.api.type.OrderStatus;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
public interface OrderRepository extends CrudRepository<Order, Integer> {
    Order[] findByRequesterIdOrderByCreatedDesc(Integer requesterId);
    Order[] findByRequesterIdAndStatusOrderByCreatedDesc(Integer requesterId, OrderStatus status);
}
