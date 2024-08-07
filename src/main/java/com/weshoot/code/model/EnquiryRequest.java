package com.weshoot.code.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EnquiryRequest {
    private String customerName;
    private Long customerPhone;
    private String customerEmail;
    private Date functionDate;
    private String description;
}
