package com.orderProcessTesting.tests.models;

import com.google.gson.GsonBuilder;
import com.orderProcessTesting.testware.utility.DataGenerator;

public class ProcessOrderModel {

    private long orderId;
    private String orderDescription;
    private String orderStatus;
    private String lastUpdatedTimestamp;
    private Boolean specialOrder;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    public void setLastUpdatedTimestamp(String lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    public Boolean getSpecialOrder() {
        return specialOrder;
    }

    public void setSpecialOrder(Boolean specialOrder) {
        this.specialOrder = specialOrder;
    }

    /******** helper methods ******/
    public ProcessOrderModel withOrderDetailsDefaultData() {
        return this
                .withOrderId(DataGenerator.getRandomNumberOfDigits(6))
                .withOrderDescription(DataGenerator.generateRandomStringWithSpaces())
                .withOrderStatus(DataGenerator.generateRandomStatus())
                .withLastUpdatedTimestamp(DataGenerator.getCurrentDateTimeTimeStamp())
                .withSpecialOrder(DataGenerator.getRandomBoolean());
    }

    public ProcessOrderModel withOrderId(long orderId) {
        this.orderId = orderId;
        return this;
    }

    public ProcessOrderModel withOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public ProcessOrderModel withOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public ProcessOrderModel withLastUpdatedTimestamp(String lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
        return this;
    }

    public ProcessOrderModel withSpecialOrder(boolean specialOrder) {
        this.specialOrder = specialOrder;
        return this;
    }

    public String toJSONString() {
        return new GsonBuilder().create().toJson(this);
    }
}
