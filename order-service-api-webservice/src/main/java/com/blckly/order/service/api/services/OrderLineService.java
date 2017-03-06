package com.blckly.order.service.api.services;

import com.blckly.order.service.api.domain.OrderLine;
import com.blckly.order.service.api.type.OrderLineStatus;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
public interface OrderLineService {
    OrderLine findOne(Integer id);
    OrderLine save(OrderLine orderLine);
    OrderLine[] save(Integer orderId, OrderLine[] orderLines);
    OrderLine[] findByOrderId(Integer orderId);
    OrderLine[] findByOrderIdAndCustomerId(Integer orderId, Integer customerId);
    OrderLine[] findByOrderIdCustomerIdAndStatus(Integer orderId, Integer customerId, OrderLineStatus status);
    OrderLine[] findByCustomerId(Integer customerId);
    OrderLine[] findByCustomerIdAndStatus(Integer customerId, OrderLineStatus status);
    void delete(Integer id);
}
