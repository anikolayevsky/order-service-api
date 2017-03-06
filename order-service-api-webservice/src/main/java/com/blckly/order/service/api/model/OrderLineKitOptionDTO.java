package com.blckly.order.service.api.model;

import com.blckly.order.service.api.type.KitOptionCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderLineKitOptionDTO {
    private Integer id;
    private List<OrderLineKitOptionItemsDTO> orderLineKitOptionItems;
    private String name;
    private String description;
    private Double baseFee;
    private KitOptionCategory category;
    private Date created;
    private Date lastModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderLineKitOptionItemsDTO> getOrderLineKitOptionItems() {
        return orderLineKitOptionItems;
    }

    public void setOrderLineKitOptionItems(List<OrderLineKitOptionItemsDTO> orderLineKitOptionItems) {
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
