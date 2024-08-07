package com.weshoot.code.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "enquiries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Transactional
public class Enquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enquiry_sequence")
    @SequenceGenerator(name = "enquiry_sequence", sequenceName = "enquiry_sequence", initialValue= 200, allocationSize = 1)
    private long enquiryId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "function_date")
    private Date functionDate;

    @Column(name = "enquiry_date")
    private Date enquiryDate;

    @Column(name = "description")
    private String description;

    @Column(name = "is_contacted")
    private boolean isContacted;
}
