package com.blckly.order.service.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderLineKitDTO {
    private Integer id;
    private List<OrderLineKitOptionDTO> orderLineKitOptions;
    private Integer qty;
    private String name;
    private String description;
    private Double baseFee;
    private String sku;
    private Date created;
    private Date lastModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderLineKitOptionDTO> getOrderLineKitOptions() {
        return orderLineKitOptions;
    }

    public void setOrderLineKitOptions(List<OrderLineKitOptionDTO> orderLineKitOptions) {
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
