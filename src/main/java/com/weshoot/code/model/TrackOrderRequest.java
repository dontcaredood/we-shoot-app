package com.weshoot.code.model;

import lombok.*;

@Data
@Builder
public class TrackOrderRequest {
    private long orderId;
    private Double amount;
    private String deliveryStatus;
    private String orderStatus;
}
