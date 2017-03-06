package com.blckly.order.service.api.domain;

import com.blckly.order.service.api.type.OrderLineStatus;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@Entity
@Table(name = "b_order_line")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="order_id")
    private Integer orderId;

    @OneToMany(mappedBy = "orderLine", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderLineKit> orderLineKits;

    @Column(name="price")
    private Double price;

    @Column(name="customer_id")
    private Integer customerId;

    @Column(name="destination_first_name")
    private String destinationFirstName;

    @Column(name="destination_last_name")
    private String destinationLastName;

    @Column(name="destination_email")
    private String destinationEmail;

    @Column(name="destination_phone")
    private String destinationPhone;
    
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="addressLine1", column=@Column(name="destination_address_line_1")),
            @AttributeOverride(name="addressLine2", column=@Column(name="destination_address_line_2")),
            @AttributeOverride(name="city", column=@Column(name="destination_city")),
            @AttributeOverride(name="state", column=@Column(name="destination_state")),
            @AttributeOverride(name="zip", column=@Column(name="destination_zip")),
            @AttributeOverride(name="country", column=@Column(name="destination_country")),
            @AttributeOverride(name="latitude", column=@Column(name="destination_latitude")),
            @AttributeOverride(name="longitude", column=@Column(name="destination_longitude"))
    })
    private Address destinationAddress;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderLineStatus status;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<OrderLineKit> getOrderLineKits() {
        return orderLineKits;
    }

    public void setOrderLineKits(List<OrderLineKit> orderLineKits) {
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

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(Address destinationAddress) {
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
