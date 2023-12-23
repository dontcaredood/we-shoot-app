package com.weshoot.code.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence")
    @SequenceGenerator(name = "orders_sequence", sequenceName = "orders_sequence", allocationSize = 1)
    private long orderId;
    @Column(name = "order_amount")
    private Double orderAmount;
    @Column(name = "event_date")
    private Date eventDate;
    @Column(name = "order_date")
    private Date orderDate;
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
    private Double remainingBalance;
    @Column(name = "order_type")
    private String orderType;
	@Column(name = "description")
	private String orderDescription;


}
