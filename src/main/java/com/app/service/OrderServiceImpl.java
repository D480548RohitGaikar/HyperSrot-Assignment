package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.InvalidCouponException;
import com.app.custom_exceptions.OrderAlreadyPaidException;
import com.app.custom_exceptions.OrderNotFoundException;
import com.app.custom_exceptions.ProductNotFoundException;
import com.app.custom_exceptions.UnauthorizedAccessException;
import com.app.custom_exceptions.UserNotFoundException;
import com.app.dao.OrderDao;
import com.app.dto.OrderDetailsWithTransactions;
import com.app.entities.Coupon;
import com.app.entities.Order;
import com.app.entities.Product;
import com.app.entities.Transaction;
import com.app.entities.User;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderDao orderDao;
    
    @Autowired
    private CouponService couponService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private InventoryService inventoryService;
    
    @Autowired
    private TransactionService transactionService;

    @Override
    public Order placeOrder(Long userId, int qty, String couponCode, Long productId) {
        // Retrieving the product from the database
        Optional<Product> productOptional = inventoryService.getProductById(productId);
        if (!productOptional.isPresent()) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        Product product = productOptional.get();

        // Retrieving the user from the database
        Optional<User> userOptional = userService.getUserById(userId);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        User user = userOptional.get();

        // Calculating the total price based on quantity and product price
        double totalPrice = product.getPrice() * qty;

        // Retrieving the coupon from the database
        Coupon coupon = couponService.getCouponByCode(couponCode);
        if (coupon == null) {
            throw new InvalidCouponException("Invalid coupon code: " + couponCode);
        }

        // Applying coupon discount if applicable
        double finalAmount = totalPrice;
        if (isCouponValid(coupon)) {
            finalAmount = applyCouponDiscount(totalPrice, coupon);
        } else {
            throw new InvalidCouponException("Invalid coupon code: " + couponCode);
        }

        // Creating the order and save it
        Order order = new Order();
        order.setUser(user);
        order.setProductId(product);
        order.setQuantity(qty);
        order.setCoupon(coupon); 
        order.setAmount(finalAmount);
        orderDao.save(order);

        return order;
    }

    private boolean isCouponValid(Coupon coupon) {
        // Checking if the coupon expiry date is after the current date
        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate = coupon.getExpiryDate();
        
        return expiryDate.isAfter(currentDate);
    }


    @Override
    public Transaction makePayment(Long userId, Long orderId, double amount) {
        Optional<Order> optionalOrder = orderDao.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (!order.getUser().getId().equals(userId)) {
                throw new UnauthorizedAccessException("User is not authorized to make payment for this order");
            }

            if ("Paid".equals(order.getStatus())) { 
                throw new OrderAlreadyPaidException("Order is already paid for");
            }

            // Setting order status as paid and save
            order.setStatus("Paid"); 
            orderDao.save(order);

            // Reloading the order from the database
            order = orderDao.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

            // Creating and saving the transaction
            Transaction transaction = new Transaction();
            transaction.setUser(order.getUser());
            transaction.setOrder(order);
            transaction.setAmount(amount);
            transaction.setStatus("Successful"); 
            return transactionService.saveTransaction(transaction);
        } else {
            throw new OrderNotFoundException("Order not found with ID: " + orderId);
        }
    }


    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDao.findByUser_Id(userId);
    }

    @Override
    public OrderDetailsWithTransactions getOrderDetailsWithTransactions(Long userId, Long orderId) {
        Optional<Order> optionalOrder = orderDao.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.getUser().getId().equals(userId)) {
                List<Transaction> transactions = transactionService.getTransactionsByOrderId(orderId); // Implement this method in TransactionService
                String couponCode = order.getCoupon() != null ? order.getCoupon().getCode() : null;
                return new OrderDetailsWithTransactions(orderId, order.getAmount(), order.getDate(), couponCode, transactions);
            } else {
                throw new UnauthorizedAccessException("User is not authorized to view details of this order");
            }
        } else {
            throw new OrderNotFoundException("Order not found with ID: " + orderId);
        }
    }


    // Method to apply coupon discount
    private double applyCouponDiscount(double totalPrice, Coupon coupon) {
        if (coupon != null) {
            switch (coupon.getCode()) {
                case "NEW5":
                    return totalPrice * 0.95; // 5% discount
                case "NEW10":
                	return totalPrice * 0.90; // 10% discount
                default:
                    return totalPrice; // No discount for unknown coupons
            }
        } else {
            return totalPrice; // No coupon applied
        }
    }
}
