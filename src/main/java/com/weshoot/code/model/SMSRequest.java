package com.weshoot.code.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SMSRequest {
    private String number;
    private String name;
    private String message;
    private long orderId;
}
