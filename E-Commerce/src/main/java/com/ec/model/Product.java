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
import com.ec.model.Seller;

@Entity
@Table(name = "ec_products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "product_seller_id", nullable = false, referencedColumnName = "seller_id")
    private Seller seller;

    @Column(name = "product_unique_id")
    private String productUniqueId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private float productPrice;

    @Column(name = "product_details")
    private String productDetails;

    @Column(name = "product_available_quantity")
    private int productAvailableQauntity;

    @Column(name = "product_rating")
    private float productRating;

    private boolean status;
}
