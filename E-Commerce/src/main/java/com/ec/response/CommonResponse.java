/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.response;

import lombok.Data;

@Data
public class CommonResponse {

    int code;
    String message;

    public CommonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
