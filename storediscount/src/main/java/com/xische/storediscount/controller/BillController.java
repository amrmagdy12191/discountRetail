package com.xische.storediscount.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xische.storediscount.api.proxy.BillRequestProxy;
import com.xische.storediscount.api.request.BillRequest;
import com.xische.storediscount.api.response.CalculateBillResponse;
import com.xische.storediscount.api.response.Response;
import com.xische.storediscount.api.validator.BillRequestValidator;
import com.xische.storediscount.model.Bill;
import com.xische.storediscount.service.BillService;
import com.xische.storediscount.utils.Constants;



@RestController
@RequestMapping("/api/bill")
public class BillController {
	 private static final Logger logger = LoggerFactory.getLogger(BillController.class);

	@Autowired
	BillRequestValidator billRequestValidator;
	
	@Autowired
    private BillService billService;

    @PostMapping("/calculate")
    public Response<CalculateBillResponse> calculateBillAmount(@RequestBody BillRequest billRequest) {
    	logger.info("calculateBillAmount start");
    	Response<CalculateBillResponse> response = new Response<CalculateBillResponse>();
    	try {
    		if(billRequestValidator.validate(billRequest)) {
        		Bill bill = BillRequestProxy.convertBillRequestToBill(billRequest);
        		response.setResult(new CalculateBillResponse(billService.calculateBillAmount(bill)));
        		response.setStatus(Constants.SUCCESS_STATUS);
        	}else {
        		response.setStatus(Constants.FAILED_STATUS);
    			response.setErrorCode(Constants.NOT_VALID_DATA_ERROR_CODE);
    			response.setErrorDescription(Constants.NOT_VALID_DATA_ERROR_DESC);
    			logger.error("calculateBillAmount :: Not valid data");
        	}
		} catch (Exception e) {
			response.setStatus(Constants.FAILED_STATUS);
			response.setErrorCode(Constants.ERROR_CODE);
			response.setErrorDescription(Constants.ERROR_DESCRIPTION);
			logger.error("calculateBillAmount :: Error while excuting " + e.getStackTrace());
		}
    	
    	logger.info("calculateBillAmount end");
    	return response;
    		
    }


}
