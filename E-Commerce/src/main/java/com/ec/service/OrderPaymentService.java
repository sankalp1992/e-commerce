/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.service;

import com.ec.request.OrderPaymentRequest;
import com.ec.response.CommonResponse;

/**
 *
 * @author lsgp
 */
public interface OrderPaymentService {

    public CommonResponse orderAndPayment(OrderPaymentRequest orderPaymentRequest);

}
