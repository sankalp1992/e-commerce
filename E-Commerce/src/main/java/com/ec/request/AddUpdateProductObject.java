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
public class AddUpdateProductObject {

    private String uniqueId;
    private String name;
    private float price;
    private String details;
    private int availQuantity;
    private float rating;
}
