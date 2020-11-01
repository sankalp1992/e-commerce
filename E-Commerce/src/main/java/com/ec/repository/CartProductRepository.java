/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ec.model.CartProduct;
import com.ec.model.*;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {

    @Query(value = "select * from ec_cart_products, ec_cart where cart_id = cart_products_cart_id and cart_products_cart_id = ?1 and cart_products_products_id = ?2 and ec_cart_products.status = 1;", nativeQuery = true)
    public CartProduct findProductsByCartIdAdProductId(int cartId, int productId);

}
