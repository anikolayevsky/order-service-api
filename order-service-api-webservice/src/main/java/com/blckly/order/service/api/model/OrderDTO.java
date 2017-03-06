package com.blckly.order.service.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.blckly.order.service.api.type.OrderStatus;
import com.blckly.order.service.api.type.OrderType;

import java.util.Date;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {
    private Integer id;
    private String invoiceId;
    private Double price;
    private OrderStatus status;
    private OrderType type;
    private Integer requesterId;
    private String billingFirstName;
    private String billingLastName;
    private String billingEmail;
    private String billingPhone;
    private AddressDTO billlingAddress;
    private Date created;
    private Date lastModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public Integer getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Integer requesterId) {
        this.requesterId = requesterId;
    }

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public AddressDTO getBilllingAddress() {
        return billlingAddress;
    }

    public void setBilllingAddress(AddressDTO billlingAddress) {
        this.billlingAddress = billlingAddress;
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
