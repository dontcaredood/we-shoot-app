package com.weshoot.code.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private long orderId;
    private Double orderAmount;
    private Double amountPaid;
    private Date eventDate;
    private String eventLocation;
    private String customerName;
    private Long customerPhone;
    private String customerEmail;
    private String orderType;
    private String orderDescription;

    @Override
    public String toString() {
        return "Order{" +
                "orderAmount=" + orderAmount +
                ", amountPaid=" + amountPaid +
                ", eventDate=" + eventDate +
                ", eventLocation='" + eventLocation + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhone=" + customerPhone +
                ", customerEmail='" + customerEmail + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                '}';
    }
}
