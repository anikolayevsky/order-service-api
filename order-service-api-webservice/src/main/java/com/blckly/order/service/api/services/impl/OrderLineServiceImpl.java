package com.blckly.order.service.api.services.impl;

import com.blckly.order.service.api.domain.OrderLineKit;
import com.blckly.order.service.api.domain.OrderLineKitOption;
import com.blckly.order.service.api.domain.OrderLineKitOptionItems;
import com.google.common.collect.Iterables;
import com.blckly.order.service.api.domain.OrderLine;
import com.blckly.order.service.api.respositories.OrderLineRepository;
import com.blckly.order.service.api.services.OrderLineService;
import com.blckly.order.service.api.type.OrderLineStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@Service
@Transactional
public class OrderLineServiceImpl implements OrderLineService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected OrderLineRepository repository;

    public OrderLine findOne(Integer id) {
        return repository.findOne(id);
    }

    public OrderLine save(OrderLine orderLine) {
        return repository.save(orderLine);
    }

    public OrderLine[] save(Integer orderId,OrderLine[] orderLines) {
        logger.info("Saving {} for order {}", orderLines.length, orderId);
        Set<OrderLine> saveableOrderLines = new HashSet<OrderLine>();
        for(OrderLine orderLine : orderLines) {
            orderLine.setOrderId(orderId);
            for(OrderLineKit kit : orderLine.getOrderLineKits()) {
                kit.setOrderLine(orderLine);
                for(OrderLineKitOption option : kit.getOrderLineKitOptions()) {
                    option.setOrderLineKit(kit);
                    for(OrderLineKitOptionItems item: option.getOrderLineKitOptionItems()) {
                        item.setOrderLineKitOption(option);
                    }
                }
            }
            saveableOrderLines.add(orderLine);
        }
        return Iterables.toArray(repository.save(saveableOrderLines), OrderLine.class);
    }

    public OrderLine[] findByOrderId(Integer orderId) {
        return repository.findByOrderId(orderId);
    }

    public OrderLine[] findByCustomerIdAndStatus(Integer customerId, OrderLineStatus status) {
        return repository.findByCustomerIdAndStatus(customerId, status);
    }

    public OrderLine[] findByCustomerId(Integer customerId) {
        return repository.findByCustomerId(customerId);
    }

    public OrderLine[] findByOrderIdAndCustomerId(Integer orderId, Integer customerId) {
        return repository.findByOrderIdAndCustomerId(orderId, customerId);
    }

    public OrderLine[] findByOrderIdCustomerIdAndStatus(Integer orderId, Integer customerId, OrderLineStatus status) {
        return repository.findByOrderIdAndCustomerIdAndStatus(orderId, customerId, status);
    }


    public void delete(Integer id) {
        repository.delete(id);
    }
}
