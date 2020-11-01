/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.service.impl;

import com.ec.model.Cart;
import com.ec.repository.CartRepository;
import com.ec.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lsgp
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    
    @Override
    public Cart getCartByUser(int userId) {
        return cartRepository.findCartByUserId(userId);
    }

}
