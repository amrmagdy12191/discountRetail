package com.xische.storediscount.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xische.storediscount.api.request.BillRequest;
import com.xische.storediscount.api.response.CalculateBillResponse;
import com.xische.storediscount.api.response.Response;
import com.xische.storediscount.model.*;
import com.xische.storediscount.service.BillService;
import com.xische.storediscount.utils.Constants;
import com.xische.storediscount.api.validator.BillRequestValidator;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BillController.class)
public class BillControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private BillService billService;

    @MockBean
    private BillRequestValidator billRequestValidator;

    @InjectMocks
    private BillController billController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(billController).build();
    }

    @Test
    public void testCalculateBillAmount_EmployeeDiscount() throws Exception {
        // Arrange
        User employee = new Employee("John Doe", new Date(System.currentTimeMillis()), 50000);
        employee.setUserId(1);
        BillRequest billRequest = createBillRequest((User)employee, 1000);
        
        when(billRequestValidator.validate(any())).thenReturn(true);
        when(billService.calculateBillAmount(any())).thenReturn(700.0); // 30% discount on 1000 = 700
        
        // Act & Assert
        mockMvc.perform(post("/api/bill/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(billRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.result.amount").value(700.0));
    }

    @Test
    public void testCalculateBillAmount_AffiliateDiscount() throws Exception {
        // Arrange
        User affiliate = new Affiliate("Jane Doe", new Date(System.currentTimeMillis()), "Tech");
        affiliate.setUserId(2);
        BillRequest billRequest = createBillRequest(affiliate, 1000);
        
        when(billRequestValidator.validate(any())).thenReturn(true);
        when(billService.calculateBillAmount(any())).thenReturn(850.0); // 10% discount on 1000 = 900
        
        // Act & Assert
        mockMvc.perform(post("/api/bill/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(billRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.result.amount").value(850.0));
    }

    @Test
    public void testCalculateBillAmount_CustomerDiscount() throws Exception {
        // Arrange
        User customer = new Customer("Jim Doe", new Date(System.currentTimeMillis() - 3L * 365 * 24 * 60 * 60 * 1000), "Special"); // Joined 3 years ago
        customer.setUserId(3);
        BillRequest billRequest = createBillRequest(customer, 1000);
        
        when(billRequestValidator.validate(any())).thenReturn(true);
        when(billService.calculateBillAmount(any())).thenReturn(950.0); // 5% discount on 1000 = 950
        
        // Act & Assert
        mockMvc.perform(post("/api/bill/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(billRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.result.amount").value(950.0));
    }

    @Test
    public void testCalculateBillAmount_AmountBasedDiscount() throws Exception {
        // Arrange
        User customer = new Customer("Jake Doe", new Date(System.currentTimeMillis()), "Special");
        customer.setUserId(4);
        BillRequest billRequest = createBillRequest(customer, 990); // $5 discount for every $100

        when(billRequestValidator.validate(any())).thenReturn(true);
        when(billService.calculateBillAmount(any())).thenReturn(945.0); // 45$ discount on 990
        
        // Act & Assert
        mockMvc.perform(post("/api/bill/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(billRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.result.amount").value(945.0));
    }

    @Test
    public void testCalculateBillAmount_NoDiscountOnGroceries() throws Exception {
        // Arrange
        User employee = new Employee("Alice Doe", new Date(System.currentTimeMillis()), 50000);
        employee.setUserId(5);
        BillRequest billRequest = createBillRequestWithGroceries(employee, 1000);

        when(billRequestValidator.validate(any())).thenReturn(true);
        when(billService.calculateBillAmount(any())).thenReturn(1000.0); // No discount on groceries
        
        // Act & Assert
        mockMvc.perform(post("/api/bill/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(billRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.result.amount").value(1000.0));
    }

    @Test
    public void testCalculateBillAmount_OnlyOnePercentageDiscount() throws Exception {
        // Arrange
        User employee = new Employee("Chris Doe", new Date(System.currentTimeMillis()), 50000);
        employee.setUserId(6);
        BillRequest billRequest = createBillRequest(employee, 1000);

        when(billRequestValidator.validate(any())).thenReturn(true);
        when(billService.calculateBillAmount(any())).thenReturn(700.0); // 30% discount applies
        
        // Act & Assert
        mockMvc.perform(post("/api/bill/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(billRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.result.amount").value(700.0));
    }
    
    @Test
    void testCalculateBillAmount_InvalidRequest() {
    	BillRequest invalidBillRequest = new BillRequest();
        when(billRequestValidator.validate(invalidBillRequest)).thenReturn(false);

        Response<CalculateBillResponse> response = billController.calculateBillAmount(invalidBillRequest);

        assertEquals(Constants.FAILED_STATUS, response.getStatus());
        assertEquals(Constants.NOT_VALID_DATA_ERROR_CODE, response.getErrorCode());
        assertEquals(Constants.NOT_VALID_DATA_ERROR_DESC, response.getErrorDescription());
    }

    @Test
    void testCalculateBillAmount_ExceptionHandling() {
    	BillRequest validBillRequest = new BillRequest();
        validBillRequest.setAmount(200);
        validBillRequest.setDate(new java.util.Date());
        validBillRequest.setUser(new User() {
            @Override
            public int getUserId() {
                return 1;
            }
        });
        validBillRequest.setItems(List.of(new com.xische.storediscount.api.request.BillRequest.Item("item1", com.xische.storediscount.api.request.BillRequest.ItemCategory.OTHERS, 100.0)));

        when(billRequestValidator.validate(validBillRequest)).thenReturn(true);
        doThrow(new RuntimeException("Unexpected Error")).when(billService).calculateBillAmount(any());

        Response<CalculateBillResponse> response = billController.calculateBillAmount(validBillRequest);

        assertEquals(Constants.FAILED_STATUS, response.getStatus());
        assertEquals(Constants.ERROR_CODE, response.getErrorCode());
        assertEquals(Constants.ERROR_DESCRIPTION, response.getErrorDescription());
    }

    private BillRequest createBillRequest(User user, double amount) {
        BillRequest billRequest = new BillRequest();
        billRequest.setUser(user);
        billRequest.setAmount(amount);
        billRequest.setDate(new Date(System.currentTimeMillis()));
        
        List<BillRequest.Item> items = new ArrayList<>();
        items.add(new BillRequest.Item("Item1", BillRequest.ItemCategory.OTHERS, 100));
        items.add(new BillRequest.Item("Item2", BillRequest.ItemCategory.OTHERS, 200));
        items.add(new BillRequest.Item("Item3", BillRequest.ItemCategory.OTHERS, 700));
        
        billRequest.setItems(items);
        return billRequest;
    }

    private BillRequest createBillRequestWithGroceries(User user, double amount) {
        BillRequest billRequest = new BillRequest();
        billRequest.setUser(user);
        billRequest.setAmount(amount);
        billRequest.setDate(new Date(System.currentTimeMillis()));
        
        List<BillRequest.Item> items = new ArrayList<>();
        items.add(new BillRequest.Item("GroceryItem1", BillRequest.ItemCategory.GROCERY, 1000));
        
        billRequest.setItems(items);
        return billRequest;
    }
}
