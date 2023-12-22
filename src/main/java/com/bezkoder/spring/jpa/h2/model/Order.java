package com.bezkoder.spring.jpa.h2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    @Column(name = "order_date")
    private String title;
    @Column(name = "order_amount")
    private Double description;
    @Column(name = "event_date")
    private Date eventDate;
    @Column(name = "event_location")
    private String eventLocation;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_phone")
    private Long customerPhone;
    @Column(name = "customer_email")
    private String customerEmail;
    @Column(name = "amount_paid")
    private Double amount_paid;
    @Column(name = "remaining_balance")
    private String remainingBalance;
    @Column(name = "order_type")
    private String orderType;
    @Column(name = "description")
    private String orderDescription;
}
