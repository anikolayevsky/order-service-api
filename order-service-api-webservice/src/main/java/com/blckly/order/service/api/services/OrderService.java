package com.blckly.order.service.api.services;

import com.blckly.order.service.api.domain.Order;
import com.blckly.order.service.api.type.OrderStatus;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
public interface OrderService {
    Order[] findByRequesterId(Integer requesterId);
    Order[] findByRequesterIdAndStatus(Integer requesterId, OrderStatus status);
    Order findOne(Integer id);
    Order save(Order order);
    void delete(Integer id);
}
