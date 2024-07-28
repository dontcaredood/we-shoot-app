package com.weshoot.code.controller;

import com.weshoot.code.entity.Orders;
import com.weshoot.code.model.CostReports;
import com.weshoot.code.model.Order;
import com.weshoot.code.model.OrdersResponse;
import com.weshoot.code.service.WeShootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/we-shoot")
public class WeShootController {

	@Autowired
	WeShootService weShootService;

	@GetMapping("/orders")
	public ResponseEntity<OrdersResponse> getAllOrders(){
		try{
			List<Orders> ordersList = weShootService.getAllOrders();
			long elements = ordersList.size();
			OrdersResponse ordersResponse = new OrdersResponse(elements, ordersList);
			return new ResponseEntity<>(ordersResponse, HttpStatus.OK);
		}
		catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/order/{id}")
	public ResponseEntity<Orders> getOrderById(@PathVariable Long id){
		try{
			Optional<Orders> order = weShootService.getOrderById(id);
			if(order.isPresent()){
				return new ResponseEntity<>(order.get(), HttpStatus.OK);
			}
			else{
				throw new NoSuchElementException("No order found under the id: "+id);
			}
		}
		catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update-delivery-status/{id}")
	public ResponseEntity<Orders> updateOrderDetails(@PathVariable long id, @RequestBody boolean status){
		Optional<Orders> orders = weShootService.getOrderById(id);
		if(orders.isPresent()) {
			orders.get().setClosed(status);
			orders.get().setDelivered(status);
		}else{
			throw new NoSuchElementException("No orders found");
		}
		orders = weShootService.updateDeliveryStatus(orders.get());
		return new ResponseEntity<>(orders.get(),HttpStatus.OK);
	}

	@GetMapping("/cost_reports")
	public ResponseEntity<CostReports> costReports(){
		try{
			List<Orders> orders = weShootService.getAllOrders();
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

	@PostMapping("/add-order")
	public ResponseEntity<Long> addOrder(@RequestBody Order order) {
		try {
			System.out.println(order);
			Long result = weShootService.addOrders(order);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
