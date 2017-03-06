package com.blckly.order.service.api.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.blckly.order.service.api.type.OrderLineStatus;

import java.util.Date;
import java.util.List;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderLineDTO {
    private Integer id;
    private Integer orderId;
    private List<OrderLineKitDTO> orderLineKits;
    private Double price;
    private Integer customerId;
    private String destinationFirstName;
    private String destinationLastName;
    private String destinationEmail;
    private String destinationPhone;
    private AddressDTO destinationAddress;
    private OrderLineStatus status;
    private Date created;
    private Date lastModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<OrderLineKitDTO> getOrderLineKits() {
        return orderLineKits;
    }

    public void setOrderLineKits(List<OrderLineKitDTO> orderLineKits) {
        this.orderLineKits = orderLineKits;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getDestinationFirstName() {
        return destinationFirstName;
    }

    public void setDestinationFirstName(String destinationFirstName) {
        this.destinationFirstName = destinationFirstName;
    }

    public String getDestinationLastName() {
        return destinationLastName;
    }

    public void setDestinationLastName(String destinationLastName) {
        this.destinationLastName = destinationLastName;
    }

    public String getDestinationEmail() {
        return destinationEmail;
    }

    public void setDestinationEmail(String destinationEmail) {
        this.destinationEmail = destinationEmail;
    }

    public String getDestinationPhone() {
        return destinationPhone;
    }

    public void setDestinationPhone(String destinationPhone) {
        this.destinationPhone = destinationPhone;
    }

    public AddressDTO getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(AddressDTO destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public OrderLineStatus getStatus() {
        return status;
    }

    public void setStatus(OrderLineStatus status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
