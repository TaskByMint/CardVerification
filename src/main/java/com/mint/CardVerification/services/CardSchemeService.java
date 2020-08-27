package com.mint.CardVerification.services;

import com.google.gson.Gson;
import com.mint.CardVerification.dto.PayloadResponse;
import com.mint.CardVerification.dto.thirdParty.BinListResponse;
import com.mint.CardVerification.models.CardScheme;
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

    private final CardSchemeProducer producer;

    @Autowired
    CardSchemeService(CardSchemeProducer producer) {

        this.producer = producer;
    }


    public PayloadResponse verifyCardBin(String cardbin) {
        PayloadResponse response = new PayloadResponse();
        try {
            //persist the cardBin
            CardScheme cardScheme = new CardScheme();
            cardScheme.setCardbin(cardbin);
            cardSchemeRepository.save(cardScheme);

            //call the 3rd party API
            BinListResponse binResponse = binListService.getBinList(cardbin);

            if (binResponse != null) {

                response.setScheme(binResponse.getScheme());
                response.setType(binResponse.getType());
                response.setBank(binResponse.getBank().getName());

                //publish the response asynchronously
                producer.sendMessage(response);

                //persist the record (binResponse)
                cardScheme.setSuccess(true);
                cardScheme.setBank(response.getBank());
                cardScheme.setScheme(response.getScheme());
                cardScheme.setType(response.getType());
                Gson gson = new Gson();
                String jsonResponce = gson.toJson(binResponse);
                cardScheme.setBinListResponse(jsonResponce);

            } else {
                response = null;
                cardScheme.setSuccess(false);
                try {
                    Gson gson = new Gson();
                    String jsonResponce = gson.toJson(binResponse);
                    cardScheme.setBinListResponse(jsonResponce);
                } catch (NullPointerException ex) {}
            }
            cardSchemeRepository.save(cardScheme);

            return response;
        }catch (Exception ex){
            log.error("Error getting response "+ex.toString());
            return  null;
        }

    }

}