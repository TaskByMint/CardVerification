package com.mint.CardVerification.services;

import com.mint.CardVerification.dto.thirdParty.BinListResponse;
import com.mint.CardVerification.utils.RequestResponseLoggingInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Slf4j
@Service
public class BinListService {



   @Value("${app.service.thirdparty.api.url}")
   private String ThirdPartyUrl;

   @Value("${app.service.thirdparty.connectionTimeoutInMs}")
   private int connectionTimeOutInMs;


    public BinListResponse getBinList(String cardBin) {
        String requestUrl = ThirdPartyUrl + cardBin;

        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(getClientHttpRequestFactory(connectionTimeOutInMs));
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
        try {
            return restTemplate.getForObject(requestUrl, BinListResponse.class);
        }catch (HttpClientErrorException e){
            if(e.getStatusCode() == HttpStatus.BAD_REQUEST){
                log.info("HttpClientErrorException: {}", e.getResponseBodyAsString());
                return null;
            }
        } catch (RestClientException e){
            log.info("RestClientException Exception: {} ", e);

        }
        return null;
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory(int timeout) {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }

}