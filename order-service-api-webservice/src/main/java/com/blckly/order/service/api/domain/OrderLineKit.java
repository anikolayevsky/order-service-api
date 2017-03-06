package com.blckly.order.service.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@Entity
@Table(name = "b_order_line_kit")
public class OrderLineKit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_line_id")
    public OrderLine orderLine;

    @OneToMany(mappedBy = "orderLineKit", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("category ASC, name ASC")
    private List<OrderLineKitOption> orderLineKitOptions;

    @Column(name="qty")
    private Integer qty;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="base_fee")
    private Double baseFee;

    @Column(name="sku")
    private String sku;

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

    public OrderLine getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(OrderLine orderLine) {
        this.orderLine = orderLine;
    }

    public List<OrderLineKitOption> getOrderLineKitOptions() {
        return orderLineKitOptions;
    }

    public void setOrderLineKitOptions(List<OrderLineKitOption> orderLineKitOptions) {
        this.orderLineKitOptions = orderLineKitOptions;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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
