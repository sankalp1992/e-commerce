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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ec_cart_products")
@Data
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_products_id")
    private Integer cartProductsId;

    @ManyToOne
    @JoinColumn(name = "cart_products_cart_id", nullable = false, referencedColumnName = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "cart_products_products_id", nullable = false, referencedColumnName = "product_id")
    private Product product;

    @Column(name = "cart_products_products_quantity")
    private int productQuantity;
    
    private boolean status;
}
