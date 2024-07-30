package com.weshoot.code.controller;

import com.weshoot.code.entity.OrderHistory;
import com.weshoot.code.entity.Orders;
import com.weshoot.code.model.*;
import com.weshoot.code.service.AuditHistoryService;
import com.weshoot.code.service.OrderService;
import com.weshoot.code.util.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/we-shoot/order")
public class OrdersController {
    @Autowired
    OrderService orderService;

    @Autowired
    SmsController smsController;


    @PostMapping("/addOrder")
    public ResponseEntity<Long> addOrder(@RequestBody Order order) {
        try {
            Long result = orderService.addOrders(order);

            final String message = ApplicationUtil.addOrderMessageBuild(result);
            smsController.sendSMS(new SMSRequest("+917094724707", "Santhosh", message, result));

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getOrders")
    public ResponseEntity<OrdersResponse> getAllOrders() {
        try {
            List<Orders> ordersList = orderService.getAllOrders();
            long elements = ordersList.size();
            OrdersResponse ordersResponse = new OrdersResponse(elements, ordersList);
            return new ResponseEntity<>(ordersResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        try {
            Optional<Orders> order = orderService.getOrderById(id);
            if (order.isPresent()) {
                return new ResponseEntity<>(order.get(), HttpStatus.OK);
            } else {
                throw new NoSuchElementException("No order found under the id: " + id);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<Orders> updateOrder(@RequestBody Order updatedOrder) {
        try {
            Orders order = orderService.updateOrder(updatedOrder);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/trackOrder/{id}")
    public ResponseEntity<TrackOrderResponse> getTrackOrder(@PathVariable long id) {
        try {
            TrackOrderResponse trackOrderDetails = orderService.getTrackOrderDetails(id);
            return new ResponseEntity<>(trackOrderDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/trackOrder")
    public ResponseEntity<TrackOrderResponse> insertTrackOrder(@RequestBody TrackOrderRequest trackOrderRequest) {
        try {

            TrackOrderResponse trackOrderDetails = orderService.insertTrackOrderDetails(trackOrderRequest);
            return new ResponseEntity<>(trackOrderDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
