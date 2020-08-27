package com.mint.CardVerification.controllers;

import com.mint.CardVerification.dto.CardVerifyResponse;
import com.mint.CardVerification.dto.PayloadResponse;
import com.mint.CardVerification.services.CardSchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/card-scheme")
public class CardSchemeController {

    @Autowired
    CardSchemeService cardSchemeService;


    @GetMapping(value = "/verify/{cardbin}")
    public ResponseEntity<?> verifyCard(@PathVariable("cardbin") String cardbin) {

        try{

            PayloadResponse payload = cardSchemeService.verifyCardBin(cardbin);
            if(payload!=null){
                return ResponseEntity.ok(new CardVerifyResponse(true,payload));
            }else{
                return ResponseEntity.ok(new CardVerifyResponse(false,payload));
            }


        }catch (Exception ex){
            return ResponseEntity.ok(new CardVerifyResponse(false,null));
        }


    }
}
