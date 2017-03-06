package com.blckly.order.service.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.blckly.order.service.api.domain.Order;
import com.blckly.order.service.api.domain.OrderLine;
import com.blckly.order.service.api.model.OrderLineDTO;
import com.blckly.order.service.api.services.OrderLineService;
import com.blckly.order.service.api.services.OrderService;
import com.blckly.order.service.api.type.OrderLineStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@RestController
@RequestMapping(value = "/order/orderLine")
@Api(value = "Order Line Service", description = "Order Line REST Service")
public class OrderLineController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected OrderLineService orderLineService;


    @Autowired
    protected ObjectMapper objectMapper;

    @ApiOperation(value = "saveOrderLines", nickname = "saveOrderLines")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrderLineDTO[].class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public OrderLineDTO[] saveOrderLines(@RequestParam(required=true) Integer orderId,
                                            @RequestBody OrderLineDTO[] dtos) {
        logger.info("Creating {} number of order lines for order id {}", dtos.length, orderId);
        if(validOrder(orderId)) {
            OrderLine[] orderLines = objectMapper.convertValue(dtos, OrderLine[].class);
            orderLines = orderLineService.save(orderId, orderLines);
            logger.info("Successfully created {} order lines for order with id {}", orderLines.length, orderId);
            return objectMapper.convertValue(orderLines, OrderLineDTO[].class);
        } else {
            String msg = "Order Id " + orderId + " does not exist";
            logger.error(msg);
            throw new EntityNotFoundException(msg);
        }
    }

    @ApiOperation(value = "getOrderLine", nickname = "getOrderLine")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrderLineDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public OrderLineDTO getOrderLine(@RequestParam(required=true) Integer orderId,
                                 @PathVariable Integer id) {
        logger.info("Get order line with id {}.", id);
        if(validOrder(orderId)) {
            OrderLine orderLine = orderLineService.findOne(id);
            if (orderLine != null && orderLine.getId() != null) {
                logger.info("Successfully found order line with id {}", orderLine.getId());
                if(!orderLine.getOrderId().equals(orderId)) {
                    String msg = "Order " + orderId + " and OrderLine " + orderLine.getId() + " pairing are not valid";
                    logger.warn(msg);
                    throw new EntityNotFoundException(msg);
                }
            }
            return objectMapper.convertValue(orderLine, OrderLineDTO.class);
        } else {
            String msg = "Order Id " + orderId + " does not exist";
            logger.error(msg);
            throw new EntityNotFoundException(msg);
        }
    }

    @ApiOperation(value = "findOrderLines", nickname = "findOrderLines")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrderLineDTO[].class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public OrderLineDTO[] findOrderLines(@RequestParam(required=false) Integer orderId,
                                         @RequestParam(required = false) Integer customerId,
                                         @RequestParam(required = false) OrderLineStatus status){
        OrderLine[] orderLines = null;
        if(orderId != null) {
            if (customerId == null && status == null) {
                logger.info("Finding order lines with for order id {}.", orderId);
                orderLines = orderLineService.findByOrderId(orderId);
            } else if (status == null) {
                logger.info("Finding order lines with for order id {} and customer {}.", orderId, customerId);
                orderLines = orderLineService.findByOrderIdAndCustomerId(orderId, customerId);
            } else {
                logger.info("Finding order lines with for order id {} and customer {} and status {}.", orderId, customerId, status);
                orderLines = orderLineService.findByOrderIdCustomerIdAndStatus(orderId, customerId, status);
            }
        } else {
            if(status == null) {
                logger.info("Finding order lines with for customer id {}.", customerId);
                orderLines = orderLineService.findByCustomerId(customerId);
            } else {
                logger.info("Finding order lines with for customer id {} and status.", customerId, status);
                orderLines = orderLineService.findByCustomerIdAndStatus(customerId, status);
            }
        }

        if(orderLines != null || orderLines.length > 0) {
            return objectMapper.convertValue(orderLines, OrderLineDTO[].class);
        } else {
            return new OrderLineDTO[0];
        }
    }

    @ApiOperation(value = "updateOrderLine", nickname = "updateOrderLine")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrderLineDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public OrderLineDTO updateOrderLine(@PathVariable Integer id, @RequestParam(required=true) Integer orderId, @RequestBody OrderLineDTO dto) throws Exception {
        logger.info("Updating orderLine with id {}", id);
        if (!id.equals(dto.getId())) {
            logger.warn("Order Line " + id + " does not match the requested id of " + dto.getId());
            throw new Exception("Id " + id + " does not match the requested id of " + dto.getId());
        }
        if(validOrder(orderId)) {
            OrderLine baseOrderLine = orderLineService.findOne(id);
            if (baseOrderLine == null) {
                throw new EntityNotFoundException("Unable to find order line with id: " + id);
            }
            if(!baseOrderLine.getOrderId().equals(orderId)) {
                String msg = "Order " + orderId + " and OrderLine " + baseOrderLine.getId() + " pairing are not valid";
                logger.warn(msg);
                throw new EntityNotFoundException(msg);
            }
            OrderLine updatedOrderLine;
            updatedOrderLine = objectMapper.convertValue(dto, OrderLine.class);
            updatedOrderLine = orderLineService.save(updatedOrderLine);
            logger.info("Successfully updated new order line with id {}", updatedOrderLine.getId());
            return objectMapper.convertValue(updatedOrderLine, OrderLineDTO.class);
        } else {
            String msg = "Order Id " + orderId + " does not exist";
            logger.error(msg);
            throw new EntityNotFoundException(msg);
        }
    }

    protected Boolean validOrder(Integer orderId) throws EntityNotFoundException {
        //add validation
        return true;
    }
}