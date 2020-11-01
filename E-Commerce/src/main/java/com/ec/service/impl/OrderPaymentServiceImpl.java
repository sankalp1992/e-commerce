/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.service.impl;

import com.ec.repository.CartRepository;
import com.ec.request.OrderPaymentRequest;
import com.ec.model.*;
import com.ec.repository.OrderRepository;
import com.ec.repository.PaymentsRepository;
import com.ec.repository.UserRepository;
import com.ec.response.CommonResponse;
import com.ec.service.OrderPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lsgp
 */
@Service
public class OrderPaymentServiceImpl implements OrderPaymentService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommonResponse orderAndPayment(OrderPaymentRequest orderPaymentRequest) {
        CommonResponse commonResponse = new CommonResponse(2005, "Order placed successfully");
        User user = userRepository.findUserByUserId(); //check if cart is already available
        if (user != null) {
            Cart cart = cartRepository.findCartByUserId(user.getUserId());
            if (cart != null) {
                if (orderPaymentRequest.getPaymentStatus().equalsIgnoreCase("Success")) {
                    Order order = new Order();
                    order.setCart(cart);
                    order.setOrderDateTime(orderPaymentRequest.getOrderDate());
                    order.setOrderDeliveryAddress(orderPaymentRequest.getDeliveryAddress());
                    order.setOrderExpectedDateTime(orderPaymentRequest.getOrderEta());
                    order.setStatus(true);
                    orderRepository.save(order);

                    Payments payments = new Payments();
                    payments.setOrder(order);
                    payments.setPaymentDateTime(orderPaymentRequest.getPaymentDate());
                    payments.setPaymentOption(orderPaymentRequest.getPaymentOption());
                    payments.setPaymentStatus(orderPaymentRequest.getPaymentStatus());
                    payments.setPaymentTotal(cart.getCartTotalPrice());
                    payments.setStatus(true);
                    paymentsRepository.save(payments);
                } else {
                    commonResponse = new CommonResponse(3008, "Failed to place order. Payment failure!! Please try again.");
                }
            } else {
                commonResponse = new CommonResponse(3006, "Cart not found");
            }
        } else {
            commonResponse = new CommonResponse(3007, "User not found");
        }
        return commonResponse;
    }

}
