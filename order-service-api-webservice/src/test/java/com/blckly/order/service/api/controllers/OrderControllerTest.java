package com.blckly.order.service.api.controllers;

import com.blckly.order.service.api.OrderServiceApiApplication;
import com.blckly.order.service.api.model.OrderDTO;
import com.blckly.order.service.api.respositories.OrderRepository;
import com.blckly.order.service.api.type.OrderStatus;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;

import static junit.framework.TestCase.fail;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {OrderServiceApiApplication.class})
@TestPropertySource(locations="classpath:application-test.properties")
@WebIntegrationTest
public class OrderControllerTest {
    @Autowired
    private OrderController controller;

    @Autowired
    protected OrderRepository repository;

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testSaveOrder() {
        OrderDTO orderDTO = generateOrder();
        Assert.isNull(orderDTO.getId());
        orderDTO = controller.saveOrder(orderDTO);
        Assert.notNull(orderDTO);
        Assert.notNull(orderDTO.getId());
    }

    @Test
    public void testGetOrder() {
        OrderDTO orderDTO = controller.getOrder(1);
        Assert.isNull(orderDTO);

        orderDTO = controller.saveOrder(generateOrder());
        orderDTO = controller.getOrder(orderDTO.getId());
        Assert.notNull(orderDTO);
    }

    @Test
    public void testFindOrder() {
        OrderDTO[] ordersDTO = controller.findOrders(1, OrderStatus.PROCESSING);
        Assert.isTrue(ordersDTO.length == 0);

        controller.saveOrder(generateOrder());
        ordersDTO = controller.findOrders(1, OrderStatus.PROCESSING);
        Assert.notNull(ordersDTO);
        Assert.isTrue(ordersDTO.length == 1);
    }

    @Test
    public void testUpdateOrder() {
        OrderDTO orderDTO = generateOrder();

        try {
            controller.updateOrder(1, null);
            org.junit.Assert.fail("Should fail");
        } catch (Exception e) {
            Assert.isInstanceOf(NullPointerException.class, e);
        }

        // Test: update with null/different order id vs the provided id
        try {
            controller.updateOrder(1, orderDTO);
            org.junit.Assert.fail("fail");
        } catch (Exception e) {
            Assert.isInstanceOf(Exception.class, e);
        }

        // Test: update a nonexistent order
        try {
            orderDTO.setId(1);
            controller.updateOrder(1, orderDTO);
            org.junit.Assert.fail("Should fail");
        } catch (Exception e) {
            Assert.isInstanceOf(EntityNotFoundException.class, e);
        }

        // Test: update a order
        orderDTO = controller.saveOrder(generateOrder());
        Double originalPrice = orderDTO.getPrice();
        orderDTO.setPrice(40.00);
        try {
            orderDTO = controller.updateOrder(orderDTO.getId(), orderDTO);
            Assert.isTrue(!orderDTO.getPrice().equals(originalPrice));
        } catch (Exception e) {
            fail();
        }

    }


    private OrderDTO generateOrder() {
        OrderDTO order = new OrderDTO();
        order.setPrice(30.00);
        order.setRequesterId(1);
        order.setStatus(OrderStatus.PROCESSING);
        return order;
    }
}
