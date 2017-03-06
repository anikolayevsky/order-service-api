package com.blckly.order.service.api.services.impl;

import com.blckly.order.service.api.domain.Order;
import com.blckly.order.service.api.respositories.OrderRepository;
import com.blckly.order.service.api.services.OrderService;
import com.blckly.order.service.api.type.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    protected OrderRepository repository;

    public Order findOne(Integer id) {
        return repository.findOne(id);
    }

    public Order save(Order order) {
        return repository.save(order);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }

    public Order[] findByRequesterId(Integer requesterId) {
        return repository.findByRequesterIdOrderByCreatedDesc(requesterId);
    }

    public Order[] findByRequesterIdAndStatus(Integer requesterId, OrderStatus status) {
        return repository.findByRequesterIdAndStatusOrderByCreatedDesc(requesterId, status);
    }
}
