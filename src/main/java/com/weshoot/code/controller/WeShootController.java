package com.weshoot.code.controller;

import com.weshoot.code.entity.Orders;
import com.weshoot.code.service.AuditHistoryService;
import com.weshoot.code.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/we-shoot")
public class WeShootController {

	@Autowired
	OrderService orderService;

	@Autowired
	SmsController smsController;

	@Autowired
	AuditHistoryService auditHistoryService;




	@PutMapping("/update-delivery-status/{id}")
	public ResponseEntity<Orders> updateOrderDetails(@PathVariable long id, @RequestBody boolean status){
		Optional<Orders> orders = orderService.getOrderById(id);
		if(orders.isPresent()) {
			orders.get().setClosed(status);
			orders.get().setDelivered(status);
		}else{
			throw new NoSuchElementException("No orders found");
		}
		orders = orderService.updateDeliveryStatus(orders.get());
		return new ResponseEntity<>(orders.get(),HttpStatus.OK);
	}






}
