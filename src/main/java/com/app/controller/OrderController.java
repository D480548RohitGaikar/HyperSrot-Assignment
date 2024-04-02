package com.app.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.custom_exceptions.BankPaymentFailedException;
import com.app.custom_exceptions.InvalidOrderException;
import com.app.custom_exceptions.OrderNotFoundException;
import com.app.custom_exceptions.PaymentFailedException;
import com.app.custom_exceptions.PaymentServerNotRespondingException;
import com.app.custom_exceptions.OrderAlreadyPaidException;
import com.app.custom_exceptions.UnauthorizedAccessException;
import com.app.dto.ErrorResponse;
import com.app.dto.OrderDTO;
import com.app.dto.OrderDetailsWithTransactions;
import com.app.dto.PaymentResponse;
import com.app.entities.Order;
import com.app.entities.Transaction;
import com.app.service.OrderService;




@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @PostMapping("/{userId}/order")
    public ResponseEntity<?> placeOrder(@PathVariable Long userId, 
                                         @RequestParam int qty, 
                                         @RequestParam String couponCode,
                                         @RequestParam Long productId) {
        // Calling the service method to place the order
        Order order = orderService.placeOrder(userId, qty, couponCode, productId);

        // Calculating the final amount after applying the coupon discount
        double finalAmount = order.getAmount();
        
        // Returning the response in the desired format
        Map<String, Object> response = new HashMap<>();
        response.put("orderId", order.getId());
        response.put("userId", userId);
        response.put("quantity", qty);
        response.put("amount", finalAmount);
        response.put("coupon", couponCode);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/{userId}/orders/{orderId}/pay")
    public ResponseEntity<?> makePayment(@PathVariable Long userId, @PathVariable Long orderId, @RequestParam double amount) {
        try {
            // Calling the service method to process payment
            Transaction transaction = orderService.makePayment(userId, orderId, amount);

            // Payment successful
            PaymentResponse response = new PaymentResponse();
            response.setUserId(userId);
            response.setOrderId(orderId);
            response.setTransactionId(transaction.getId()); 
            response.setStatus("successful");
            return ResponseEntity.ok(response);
        } catch (UnauthorizedAccessException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(getErrorResponse(userId, orderId, "User is not authorized to make payment for this order"));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getErrorResponse(userId, orderId, "Order not found with ID: " + orderId));
        } catch (OrderAlreadyPaidException e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(getErrorResponse(userId, orderId, "Order is already paid for"));
        } catch (PaymentFailedException | BankPaymentFailedException | InvalidOrderException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorResponse(userId, orderId, e.getMessage()));
        } catch (PaymentServerNotRespondingException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(getErrorResponse(userId, orderId, "No response from payment server"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorResponse(userId, orderId, "An unexpected error occurred"));
        }
    }

    private ErrorResponse getErrorResponse(Long userId, Long orderId, String description) {
        ErrorResponse response = new ErrorResponse();
        response.setUserId(userId);
        response.setOrderId(orderId);
        response.setStatus("failed");
        response.setDescription(description);
        return response;
    }



    @GetMapping("/{userId}/orders")
    public ResponseEntity<?> getUserOrders(@PathVariable Long userId) {
        List<Order> userOrders = orderService.getUserOrders(userId);
        
        // Converting the list of orders to a list of DTOs containing only the required information
        List<OrderDTO> orderDTOs = userOrders.stream()
            .map(order -> new OrderDTO(order.getId(), order.getAmount(), order.getCoupon().getCode()))
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(orderDTOs);
    }


    @GetMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<?> getOrderDetails(@PathVariable Long userId, @PathVariable Long orderId) {
        OrderDetailsWithTransactions orderDetails = orderService.getOrderDetailsWithTransactions(userId, orderId);
        if (orderDetails == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(getErrorResponse(userId, orderId, "Order not found"));
        }
        return ResponseEntity.ok(orderDetails);
    }


}
