package com.weshoot.code.controller;

import com.weshoot.code.entity.Enquiry;
import com.weshoot.code.entity.Orders;
import com.weshoot.code.entity.User;
import com.weshoot.code.model.EnquiryRequest;
import com.weshoot.code.model.EnquiryResponse;
import com.weshoot.code.model.Order;
import com.weshoot.code.model.UserRequest;
import com.weshoot.code.service.EnquiryService;
import com.weshoot.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.weshoot.code.util.GlobalConstants.LOCAL_URL;
import static com.weshoot.code.util.GlobalConstants.WEB_URL;

@RestController
@RequestMapping("/we-shoot/enquiry")
public class EnquiryController {
    @Autowired
    EnquiryService enquiryService;

    @CrossOrigin(origins = {LOCAL_URL, WEB_URL}, methods = {RequestMethod.POST,RequestMethod.OPTIONS})
    @PostMapping("/addEnquiry")
    public ResponseEntity<Long> addEnquiry(@RequestBody EnquiryRequest enquiryRequest) {
        long id = enquiryService.addEnquiry(enquiryRequest);
        // Invalidate the session or perform other logout actions here

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = {LOCAL_URL, WEB_URL}, methods = {RequestMethod.GET})
    @GetMapping("/getAllEnquiries")
    public ResponseEntity<EnquiryResponse> getEnquiries() {
        List<Enquiry> enquiryList =  enquiryService.getAllEnquiries();
        EnquiryResponse response = EnquiryResponse.builder().enquiryList(enquiryList).noOfElements(enquiryList.size()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = {LOCAL_URL, WEB_URL}, methods = {RequestMethod.GET})
    @GetMapping("/getActiveEnquiries")
    public ResponseEntity<EnquiryResponse> getActiveEnquiries() {
        List<Enquiry> enquiryList =  enquiryService.getActiveEnquiries();
        EnquiryResponse response = EnquiryResponse.builder().enquiryList(enquiryList).noOfElements(enquiryList.size()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = {LOCAL_URL, WEB_URL}, methods = {RequestMethod.PUT,RequestMethod.OPTIONS})
    @PutMapping("/updateEnquiry")
    public ResponseEntity<Boolean> updateOrder(@RequestBody long enquiryId) {
        try {
            Boolean status = enquiryService.updateOrder(enquiryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
