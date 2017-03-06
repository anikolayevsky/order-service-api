package com.blckly.order.service.api.domain;

import com.blckly.order.service.api.type.OrderStatus;
import com.blckly.order.service.api.type.OrderType;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@Entity
@Table(name = "b_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Add in invoice id
    @Column(name="invoice_id")
    private String invoiceId;

    @Column(name="price")
    private Double price;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Column(name="requester_id")
    private Integer requesterId;

    @Column(name="billing_first_name")
    private String billingFirstName;

    @Column(name="billing_last_name")
    private String billingLastName;

    @Column(name="billing_email")
    private String billingEmail;

    @Column(name="billing_phone")
    private String billingPhone;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="addressLine1", column=@Column(name="billing_address_line_1")),
            @AttributeOverride(name="addressLine2", column=@Column(name="billing_address_line_2")),
            @AttributeOverride(name="city", column=@Column(name="billing_city")),
            @AttributeOverride(name="state", column=@Column(name="billing_state")),
            @AttributeOverride(name="zip", column=@Column(name="billing_zip")),
            @AttributeOverride(name="country", column=@Column(name="billing_country")),
            @AttributeOverride(name="latitude", column=@Column(name="billing_latitude")),
            @AttributeOverride(name="longitude", column=@Column(name="billing_longitude"))
    })
    private Address billingAddress;
    
    @Column(name = "created", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "lastmodified")
    @Temporal(TemporalType.TIMESTAMP)
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

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
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
