package com.weshoot.code.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TrackOrderResponse {
    private long orderId;
    private Double orderAmount;
    private Double amountPaid;
    private Double balanceAmount;
    private String orderStatus;
    private String deliveryStatus;
}
