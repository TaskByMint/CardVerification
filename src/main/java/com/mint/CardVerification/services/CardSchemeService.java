package com.mint.CardVerification.services;

import com.mint.CardVerification.dto.PayloadResponse;
import com.mint.CardVerification.dto.thirdParty.BinListResponse;
import com.mint.CardVerification.repository.CardSchemeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardSchemeService {

 @Autowired
 CardSchemeRepository cardSchemeRepository;

 @Autowired
 BinListService binListService;



    public PayloadResponse verifyCardBin(String cardbin) {
        PayloadResponse response = new PayloadResponse();

        //call the 3rd party API
        BinListResponse binResponse = binListService.getBinList(cardbin);

        if(binResponse!=null) {

            response.setScheme(binResponse.getScheme());
            response.setType(binResponse.getType());
            response.setBank(binResponse.getBank().getName());

            //publish the response asynchronously
          //  producer.sendMessage(response);
        }else{
            response = null;
        }

        //persist the record (binResponse)


        return response;

    }

}