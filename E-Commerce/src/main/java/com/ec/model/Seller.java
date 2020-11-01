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
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ec_seller")
@Data
public class Seller {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "seller_contact_email")
    private String userContactEmail;

    @Column(name = "seller_contact_number")
    private String userContactNumber;

    @Column(name = "seller_password")
    private String userPassword;

    @Column(name = "seller_address")
    private String userAddress;

    @Column(name = "seller_rating")
    private float userRating;

    @Column(name = "seller_access_token")
    private String sellerAccessToken;

    private boolean status;
    
}
