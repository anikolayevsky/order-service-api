package com.blckly.order.service.api.domain;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "b_order_line_kit_option_items")
public class OrderLineKitOptionItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="order_line_kit_option_id")
    private OrderLineKitOption orderLineKitOption;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="base_fee")
    private Double baseFee;

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

    public OrderLineKitOption getOrderLineKitOption() {
        return orderLineKitOption;
    }

    public void setOrderLineKitOption(OrderLineKitOption orderLineKitOption) {
        this.orderLineKitOption = orderLineKitOption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(Double baseFee) {
        this.baseFee = baseFee;
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
