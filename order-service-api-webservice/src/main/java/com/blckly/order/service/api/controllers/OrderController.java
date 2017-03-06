package com.blckly.order.service.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.blckly.order.service.api.domain.Order;
import com.blckly.order.service.api.helpers.OrderHelper;
import com.blckly.order.service.api.model.OrderDTO;
import com.blckly.order.service.api.services.OrderService;
import com.blckly.order.service.api.type.OrderStatus;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@RestController
@RequestMapping(value = "/order")
@Api(value = "Order Service", description = "Order REST Service")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected ObjectMapper objectMapper;


    @ApiOperation(value = "saveOrder", nickname = "saveOrder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrderDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public OrderDTO saveOrder(@RequestBody OrderDTO dto) {
        logger.info("Creating new order");
        Order order = objectMapper.convertValue(dto, Order.class);
        //generate invoice
        String invoiceId = OrderHelper.generateInvoiceId();
        order.setInvoiceId(invoiceId);
        orderService.save(order);
        logger.info("Successfully created new order with id {}", order.getId());
        return objectMapper.convertValue(order, OrderDTO.class);
    }

    @ApiOperation(value = "getOrder", nickname = "getOrder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrderDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public OrderDTO getOrder(@PathVariable Integer id) {
        logger.info("Finding order with id {}.", id);
        Order order = orderService.findOne(id);
        if(order != null && order.getId() != null) {
            logger.info("Successfully found order with id {}", order.getId());
        }
        return objectMapper.convertValue(order, OrderDTO.class);
    }

    @ApiOperation(value = "findOrders", nickname = "findOrders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrderDTO[].class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value="", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public OrderDTO[] findOrders(@RequestParam Integer requesterId,
                                 @RequestParam(required = false) OrderStatus status) {
        //Make pageable
        logger.info("Finding orders with for requester id {}.", requesterId);
        Order[] orders = null;
        if(status == null) {
            orders = orderService.findByRequesterId(requesterId);
        } else {
            orders = orderService.findByRequesterIdAndStatus(requesterId, status);
        }
        return objectMapper.convertValue(orders, OrderDTO[].class);
    }

    @ApiOperation(value = "updateOrder", nickname = "updateOrder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrderDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value="/{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public OrderDTO updateOrder(@PathVariable Integer id, @RequestBody OrderDTO dto) throws Exception {
        logger.info("Updating order with id {}", id);
        if(!id.equals(dto.getId())) {
            logger.warn("Id " + id + " does not match the requested id of " + dto.getId());
            throw new Exception("Id " + id + " does not match the requested id of " + dto.getId());
        }
        Order order = orderService.findOne(id);
        if (order == null) {
            throw new EntityNotFoundException("Unable to find order with id: " + id);
        }

        order = objectMapper.convertValue(dto, Order.class);
        order = orderService.save(order);
        logger.info("Successfully updated new order with id {}", order.getId());
        return objectMapper.convertValue(order, OrderDTO.class);
    }

//    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
//    public void deleteOrder(@PathVariable Integer id) {
//        logger.info("Deleting order with id {}", id);
//        orderService.delete(id);
//    }

}
