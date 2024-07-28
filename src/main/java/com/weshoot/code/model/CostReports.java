package com.weshoot.code.model;

import lombok.Data;

@Data
public class CostReports {
    private long totalOrder;
    private int completedOrders;
    private int deliveredOrders;
    private Double totalRevenue;
    private Double totalExpense;
    private Double totalProfit;
}
