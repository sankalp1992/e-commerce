/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ec.model.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from ec_user limit 1", nativeQuery = true)
    public User findUserByUserId();

}
