/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ec.model.Cart;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query(value = "select * from ec_cart, ec_user where cart_user_id = user_id and user_id = ?1 and ec_cart.status = 1", nativeQuery = true)
    public Cart findCartByUserId(int userId);

    public Cart findByCartIdAndStatus(int cartId, boolean status);

}
