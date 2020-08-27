/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mint.CardVerification.repository;


import com.mint.CardVerification.models.CardScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */
@Repository
public interface CardSchemeRepository extends JpaRepository<CardScheme, Long>{
    
}
