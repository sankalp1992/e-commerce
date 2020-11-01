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
@Table(name = "ec_payments")
@Data
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payments_id")
    private Integer paymentsId;

    @OneToOne
    @JoinColumn(name = "payments_order_id", nullable = false, referencedColumnName = "order_id")
    private Order order;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "payment_option")
    private String paymentOption;

    @Column(name = "payment_total")
    private float paymentTotal;

    @Column(name = "payment_date_time")
    private String paymentDateTime;

    private boolean status;

}
