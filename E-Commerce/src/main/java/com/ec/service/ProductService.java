/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.service;

import com.ec.model.Product;
import com.ec.request.AddUpdateProductObject;
import com.ec.response.AddUpdateProductResponse;
import com.ec.response.ProductObject;
import java.util.List;
import com.ec.model.Seller;
import com.ec.request.AddToCartRequest;
import com.ec.response.CommonResponse;

/**
 *
 * @author lsgp
 */
public interface ProductService {

    public List<ProductObject> getAllProducts();

    public AddUpdateProductResponse addUpdateProduct(List<AddUpdateProductObject> productList, Seller seller);

    public Product getProductByUniqueId(String uniqueId);

    public CommonResponse addProductToCart(AddToCartRequest addToCartRequest);

}
