package com.weshoot.code.controller;
import com.weshoot.code.model.SMSRequest;
import com.weshoot.code.service.AuditHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import static com.weshoot.code.util.GlobalConstants.LOCAL_URL;
import static com.weshoot.code.util.GlobalConstants.WEB_URL;

@CrossOrigin(origins = {LOCAL_URL, WEB_URL})
@RestController
@RequestMapping("/we-shoot/notify")
public class SmsController {
    @Autowired
    AuditHistoryService auditHistoryService;
    
    @CrossOrigin(origins = {LOCAL_URL, WEB_URL})
    @GetMapping(value = "/sendSMS/{number}")
    public void sendSMS(@RequestBody SMSRequest smsRequest) {
        final String FROM_NUMBER = "+14845529230";
        //Message.creator(new PhoneNumber(smsRequest.getNumber()),
                //new PhoneNumber(FROM_NUMBER), smsRequest.getMessage()).create();
        auditHistoryService.addSMSHistory(smsRequest.getOrderId());
        System.out.println("Message sent successfully");
    }
}