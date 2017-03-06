package com.blckly.order.service.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderLineKitOptionItemsDTO {
    private Integer id;
    private String name;
    private String description;
    private Double baseFee;
    private Date created;
    private Date lastModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
