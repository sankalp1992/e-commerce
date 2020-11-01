/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ec_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @OneToOne
    @JoinColumn(name = "order_cart_id", nullable = false, referencedColumnName = "cart_id")
    private Cart cart;

    @Column(name = "order_delivery_address")
    private String orderDeliveryAddress;

    @Column(name = "order_date_time")
    private String orderDateTime;

    @Column(name = "order_expected_date_time")
    private String orderExpectedDateTime;

    private boolean status;

}
