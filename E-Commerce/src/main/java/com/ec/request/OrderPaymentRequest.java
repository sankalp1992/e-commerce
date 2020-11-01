/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.request;

import lombok.Data;

/**
 *
 * @author lsgp
 */
@Data
public class OrderPaymentRequest {

    private String accessToken;
    private String deliveryAddress;
    private String orderDate;
    private String orderEta;
    private String paymentStatus;
    private String paymentOption;
    private String paymentDate;

}
