/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.service.impl;

import com.ec.model.Product;
import com.ec.model.Seller;
import com.ec.repository.SellerRepository;
import com.ec.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lsgp
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Seller getSellerById(int sellerId) {
        return sellerRepository.findBySellerIdAndStatus(sellerId, true);
    }
}
