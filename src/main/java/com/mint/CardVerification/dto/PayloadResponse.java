package com.mint.CardVerification.dto;


import lombok.Data;

@Data
public class PayloadResponse {

    String scheme;

    String type;

    String bank;

}
