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

import static com.weshoot.code.util.GlobalConstants.LOCAL_URL;
import static com.weshoot.code.util.GlobalConstants.WEB_URL;

@CrossOrigin(origins = {LOCAL_URL, WEB_URL})
@RestController
@RequestMapping("/we-shoot")
public class WeShootController {

	@Autowired
	OrderService orderService;

	@Autowired
	SmsController smsController;

	@Autowired
	AuditHistoryService auditHistoryService;
	@CrossOrigin(origins = {LOCAL_URL, WEB_URL}, methods = {RequestMethod.POST})
	@PostMapping("/check")
	public ResponseEntity<String> checkPath(){
		return new ResponseEntity<>("HELLO",HttpStatus.OK);
	}


	@CrossOrigin(origins = {LOCAL_URL, WEB_URL}, methods = {RequestMethod.POST})
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
