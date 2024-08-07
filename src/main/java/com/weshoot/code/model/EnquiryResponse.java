package com.weshoot.code.model;

import com.weshoot.code.entity.Enquiry;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EnquiryResponse {
    private int noOfElements;
    private List<Enquiry> enquiryList;
}
