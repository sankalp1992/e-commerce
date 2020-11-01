/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ec.response.CommonResponse;
import com.ec.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.ec.response.ProductObject;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.ec.request.AddUpdateProductRequest;
import com.ec.response.AddUpdateProductResponse;
import com.ec.model.*;
import com.ec.request.AddToCartRequest;
import com.ec.request.OrderPaymentRequest;
import com.ec.service.OrderPaymentService;
import com.ec.service.SellerService;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author lsgp
 */
@RestController
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private OrderPaymentService orderPaymentService;

    @GetMapping(path = "/listProduct") // Map ONLY POST Requests
    @ResponseBody
    private Object listProduct(@RequestParam(required = false) String accessToken) {

        LOGGER.info("Requested URL : /listProduct" + accessToken);
        CommonResponse commonResponse;
        Map<String, Object> responseMap = new HashMap<>();
        //check for user authentication
        boolean userExists = true;
        if (userExists) {
            List<ProductObject> productList = productService.getAllProducts();
            if (!productList.isEmpty()) {   //Products available
                commonResponse = new CommonResponse(2000, "Product list fetched successfully");
                responseMap.put("data", productList);
            } else {    //Products not available
                commonResponse = new CommonResponse(3001, "Products not available");
            }
        } else {    //User not autherized to fetch the data
            commonResponse = new CommonResponse(3000, "Not Autherized");
        }
        responseMap.put("response", commonResponse);
        return responseMap;
    }

    @PostMapping(path = "/addProduct") // Map ONLY POST Requests
    @ResponseBody
    private Object addProduct(@RequestBody AddUpdateProductRequest addUpdateProductRequest) {

        LOGGER.info("Requested URL : /addProduct" + addUpdateProductRequest.getAccessToken());
        CommonResponse commonResponse;
        Map<String, Object> responseMap = new HashMap<>();
        //check for user authentication
        boolean sellerAutherized = true;
        if (sellerAutherized) {
            Seller seller = sellerService.getSellerById(addUpdateProductRequest.getSellerId());
            if (seller != null) {
                AddUpdateProductResponse addUpdateProductResponse = productService.addUpdateProduct(addUpdateProductRequest.getProductList(), seller);
                commonResponse = new CommonResponse(2001, "Request processed");
                responseMap.put("successIds", addUpdateProductResponse.getSuccessUniqueIds());
                responseMap.put("failedIds", addUpdateProductResponse.getFailedUniqueIds());
            } else {
                commonResponse = new CommonResponse(3002, "Seller not found");
            }

        } else {    //User not autherized to fetch the data
            commonResponse = new CommonResponse(3000, "Not Autherized");
        }
        responseMap.put("response", commonResponse);
        return responseMap;
    }

    @PostMapping(path = "/addToCart") // Map ONLY POST Requests
    @ResponseBody
    private Object addProductToCart(@RequestBody AddToCartRequest addToCartRequest) {

        LOGGER.info("Requested URL : /addProduct" + addToCartRequest.getAccessToken());
        CommonResponse commonResponse;
        Map<String, Object> responseMap = new HashMap<>();
        //check for user authentication
        boolean userAutherized = true;
        if (userAutherized) {
            //check if product present
            commonResponse = productService.addProductToCart(addToCartRequest);
        } else {    //User not autherized to fetch the data
            commonResponse = new CommonResponse(3000, "Not Autherized");
        }
        responseMap.put("response", commonResponse);
        return responseMap;
    }

    @PostMapping(path = "/orderAndPayment") // Map ONLY POST Requests
    @ResponseBody
    private Object orderAndPayment(@RequestBody OrderPaymentRequest orderPaymentRequest) {

        LOGGER.info("Requested URL : /orderAndPayment" + orderPaymentRequest.getAccessToken());
        CommonResponse commonResponse;
        Map<String, Object> responseMap = new HashMap<>();
        //check for user authentication
        boolean userAutherized = true;
        if (userAutherized) {
            //check if product present
            commonResponse = orderPaymentService.orderAndPayment(orderPaymentRequest);
        } else {    //User not autherized to fetch the data
            commonResponse = new CommonResponse(3000, "Not Autherized");
        }
        responseMap.put("response", commonResponse);
        return responseMap;
    }

}
