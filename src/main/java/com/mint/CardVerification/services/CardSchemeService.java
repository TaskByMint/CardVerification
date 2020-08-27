package com.mint.CardVerification.services;

import com.google.gson.Gson;
import com.mint.CardVerification.dto.PayloadResponse;
import com.mint.CardVerification.dto.thirdParty.BinListResponse;
import com.mint.CardVerification.dto.CounterResponse;
import com.mint.CardVerification.exceptions.BadRequestException;
import com.mint.CardVerification.models.CardBinCounter;
import com.mint.CardVerification.models.CardScheme;
import com.mint.CardVerification.repository.CardBinCounterRepository;
import com.mint.CardVerification.repository.CardSchemeRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardSchemeService {

 @Autowired
 CardSchemeRepository cardSchemeRepository;

 @Autowired
 CardBinCounterRepository cardBinCounterRepository;

 @Autowired
 BinListService binListService;

    final int MAX_PAGE_SIZE  = 100;

    private final CardSchemeProducer producer;

    @Autowired
    CardSchemeService(CardSchemeProducer producer) {

        this.producer = producer;
    }


    public PayloadResponse verifyCardBin(String cardbin) {
        PayloadResponse response = new PayloadResponse();
        try {
            CardBinCounter cardBinCounter = cardBinCounterRepository.findByCardbin(cardbin).orElse(null);

            if(cardBinCounter!=null){//already exists
                //update counter
                cardBinCounter.setCount(cardBinCounter.getCount()+1);
                cardBinCounterRepository.save(cardBinCounter);

            }else{
                //create a new cardbin
                CardBinCounter newCard = new  CardBinCounter();
                newCard.setCardbin(cardbin);
                newCard.setCount(1);
                cardBinCounterRepository.save(newCard);
            }

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


    public CounterResponse<String> getHitCount(int start, int limit){
        validatePageNumberAndSize(0, limit);
        Pageable pageable = PageRequest.of(0, limit, Sort.Direction.DESC, "id");
        Page<CardBinCounter> cardBinCounters = cardBinCounterRepository.findAll(pageable);

        JSONObject obj = new JSONObject();
        if(cardBinCounters.getNumberOfElements() == 0){
            return new CounterResponse<String>(false, start,
                    limit, cardBinCounters.getTotalElements(),obj.toString());
        }


        for(CardBinCounter cardBinCounter:cardBinCounters)
        {

            String cardbin = cardBinCounter.getCardbin();
            int count = cardBinCounter.getCount();
            obj.put(cardbin, count);

        }


        return new CounterResponse<String>(true, start,
                limit, cardBinCounters.getTotalElements(),obj.toString());
    }


    public  void validatePageNumberAndSize(int page, int size){
        if(page < 0){
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > MAX_PAGE_SIZE){
            throw new BadRequestException("Page size must not be greater than " + MAX_PAGE_SIZE);
        }
    }



}