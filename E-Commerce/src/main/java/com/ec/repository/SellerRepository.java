/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ec.model.Seller;

/**
 *
 * @author lsgp
 */
public interface SellerRepository extends JpaRepository<Seller, Integer> {

    public Seller findBySellerIdAndStatus(int sellerId, boolean status);

}
