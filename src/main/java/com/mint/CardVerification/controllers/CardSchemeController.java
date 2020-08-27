package com.mint.CardVerification.controllers;

import com.mint.CardVerification.dto.CardVerifyResponse;
import com.mint.CardVerification.dto.PayloadResponse;
import com.mint.CardVerification.dto.CounterResponse;
import com.mint.CardVerification.services.CardSchemeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/stats")
    public CounterResponse<String> getHitCount(@RequestParam(value = "start") int start,
                                                   @RequestParam(value = "limit") int limit) {
        return cardSchemeService.getHitCount(start, limit);
    }
}
