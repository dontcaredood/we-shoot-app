package com.weshoot.code.controller;

import com.weshoot.code.entity.OrderHistory;
import com.weshoot.code.service.AuditHistoryService;
import com.weshoot.code.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.weshoot.code.util.GlobalConstants.LOCAL_URL;
import static com.weshoot.code.util.GlobalConstants.WEB_URL;

@CrossOrigin(origins = {LOCAL_URL, WEB_URL})
@RestController
@RequestMapping("/we-shoot/audit")
public class AuditHistoryController {
    @Autowired
    AuditHistoryService auditHistoryService;
    @CrossOrigin(origins = {LOCAL_URL, WEB_URL})
    @GetMapping("/getOrderHistory/{orderId}")
    public ResponseEntity<List<OrderHistory>> getHistoryById(@PathVariable long orderId) {
        try {
            Optional<List<OrderHistory>> orderHistories = auditHistoryService.getOrderHistoryById(orderId);
            List<OrderHistory> orderHistoriesList = orderHistories.orElseGet(List::of);
            return new ResponseEntity<>(orderHistoriesList, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
