package com.weshoot.code.service;

import com.weshoot.code.entity.Enquiry;
import com.weshoot.code.entity.Orders;
import com.weshoot.code.entity.User;
import com.weshoot.code.model.EnquiryRequest;
import com.weshoot.code.model.EnquiryResponse;
import com.weshoot.code.repository.EnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EnquiryService {

    @Autowired
    EnquiryRepository enquiryRepository;

    @Autowired
    AuditHistoryService auditHistoryService;

    public long addEnquiry(EnquiryRequest enquiryRequest) {
        return enquiryRepository.save(mapRequestToEntity(enquiryRequest)).getEnquiryId();
    }

    private Enquiry mapRequestToEntity(EnquiryRequest enquiryRequest) {
        return Enquiry.builder().customerName(enquiryRequest.getCustomerName())
                .email(enquiryRequest.getCustomerEmail())
                .description(enquiryRequest.getDescription())
                .functionDate(enquiryRequest.getFunctionDate())
                .isContacted(Boolean.FALSE)
                .phoneNumber(String.valueOf(enquiryRequest.getCustomerPhone()))
                .enquiryDate(new Date())
                .build();
    }

    public List<Enquiry> getAllEnquiries() {
        return enquiryRepository.findAll();
    }

    public List<Enquiry> getActiveEnquiries() {
        return enquiryRepository.findAllByIsContacted(Boolean.FALSE);
    }

    public Boolean updateOrder(long enquiryId) {
        return enquiryRepository.findById(enquiryId)
                .map(enquiry -> {
                    enquiry.setContacted(Boolean.TRUE);
                    Enquiry save = enquiryRepository.save(enquiry);
                    //auditHistoryService.updateOrderHistory(enquiryId,save);
                    return save;
                })
                .orElseThrow(() -> new NoSuchElementException("Order not found with id " + enquiryId)).isContacted();
    }
}
