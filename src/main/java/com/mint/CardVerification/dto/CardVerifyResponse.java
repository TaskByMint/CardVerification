package com.mint.CardVerification.dto;

import lombok.Data;

@Data
public class CardVerifyResponse {

    boolean success;

    PayloadResponse payload;

    public CardVerifyResponse(boolean success, PayloadResponse payload) {
        this.success = success;
        this.payload = payload;

    }


}
