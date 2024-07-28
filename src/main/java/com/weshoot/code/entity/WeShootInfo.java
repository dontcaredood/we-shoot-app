//package com.weshoot.code.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//
//import java.util.Date;
//
//@Entity
//@Table(name = "companyInfo")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//public class WeShootInfo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "info_sequence")
//    @SequenceGenerator(name = "info_sequence", sequenceName = "info_sequence", initialValue= 100, allocationSize = 1)
//    private long infoId;
//    @Column(name = "companyName")
//    private String companyName;
//    @Column(name = "companyEmail")
//    private Date eventDate;
//    @Column(name = "order_date")
//    private Date orderDate;
//    @Column(name = "event_location")
//    private String eventLocation;
//    @Column(name = "customer_name")
//    private String customerName;
//    @Column(name = "customer_phone")
//    private Long customerPhone;
//    @Column(name = "customer_email")
//    private String customerEmail;
//    @Column(name = "amount_paid")
//    private Double amountPaid;
//}
