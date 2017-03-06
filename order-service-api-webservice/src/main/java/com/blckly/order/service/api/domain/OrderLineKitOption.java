package com.blckly.order.service.api.domain;

import com.blckly.order.service.api.type.KitOptionCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "b_order_line_kit_option")
public class OrderLineKitOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="order_line_kit_id")
    private OrderLineKit orderLineKit;

    @OneToMany(mappedBy = "orderLineKitOption", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("name ASC")
    private List<OrderLineKitOptionItems> orderLineKitOptionItems;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="base_fee")
    private Double baseFee;

    @Column(name="category")
    @Enumerated(EnumType.STRING)
    private KitOptionCategory category;

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

    public OrderLineKit getOrderLineKit() {
        return orderLineKit;
    }

    public void setOrderLineKit(OrderLineKit orderLineKit) {
        this.orderLineKit = orderLineKit;
    }

    public List<OrderLineKitOptionItems> getOrderLineKitOptionItems() {
        return orderLineKitOptionItems;
    }

    public void setOrderLineKitOptionItems(List<OrderLineKitOptionItems> orderLineKitOptionItems) {
        this.orderLineKitOptionItems = orderLineKitOptionItems;
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

    public KitOptionCategory getCategory() {
        return category;
    }

    public void setCategory(KitOptionCategory category) {
        this.category = category;
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
