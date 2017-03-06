package com.blckly.order.service.api.respositories;

import com.blckly.order.service.api.domain.OrderLine;
import com.blckly.order.service.api.type.OrderLineStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    OrderLine[] findByCustomerId(Integer customerId);
    OrderLine[] findByCustomerIdAndStatus(Integer customerId, OrderLineStatus status);
    OrderLine[] findByOrderId(Integer orderId);
    OrderLine[] findByOrderIdAndCustomerId(Integer orderId, Integer customerId);
    OrderLine[] findByOrderIdAndCustomerIdAndStatus(Integer orderId, Integer customerId, OrderLineStatus status);
}
