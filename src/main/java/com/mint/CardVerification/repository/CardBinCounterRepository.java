/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mint.CardVerification.repository;


import com.mint.CardVerification.models.CardBinCounter;
import com.mint.CardVerification.models.CardScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author hp
 */
@Repository
public interface CardBinCounterRepository extends JpaRepository<CardBinCounter, Long>{

    Optional<CardBinCounter> findByCardbin(String cardbin);
}
