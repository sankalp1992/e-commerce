/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.request;

import java.util.List;
import lombok.Data;

/**
 *
 * @author lsgp
 */
@Data
public class AddUpdateProductRequest {

    private String accessToken;
    private int sellerId;
    private List<AddUpdateProductObject> productList;
}
