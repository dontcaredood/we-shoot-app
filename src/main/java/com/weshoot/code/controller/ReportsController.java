package com.weshoot.code.controller;

import com.weshoot.code.entity.Orders;
import com.weshoot.code.model.CostReports;
import com.weshoot.code.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.weshoot.code.util.GlobalConstants.LOCAL_URL;
import static com.weshoot.code.util.GlobalConstants.WEB_URL;

@CrossOrigin(origins = {LOCAL_URL, WEB_URL})
@RestController
@RequestMapping("/we-shoot/reports")
public class ReportsController {
    @Autowired
    OrderService orderService;
    @CrossOrigin(origins = {LOCAL_URL, WEB_URL})
    @GetMapping("/cost_reports")
    public ResponseEntity<CostReports> costReports(){
        try{
            List<Orders> orders = orderService.getAllOrders();
            List<Orders> completedOrders = orders.stream().filter(Orders::isDelivered).filter(Orders::isClosed).toList();
            List<Orders> pendingOrders = orders.stream().filter(Orders::isDelivered).toList();
            CostReports costReports = new CostReports();
            costReports.setCompletedOrders(completedOrders.size());
            costReports.setDeliveredOrders(pendingOrders.size());
            costReports.setTotalRevenue(completedOrders.stream().map(Orders::getAmountPaid).reduce(0.0,Double::sum));
            return new ResponseEntity<>(costReports, HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
