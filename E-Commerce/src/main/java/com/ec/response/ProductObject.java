/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.response;

import lombok.Data;

@Data
public class ProductObject {

    private int id;
    private int sellerId;
    private String sellerName;
    private String uniqueId;
    private String name;
    private float price;
    private String details;
    private int availQuantity;
    private float rating;
}
