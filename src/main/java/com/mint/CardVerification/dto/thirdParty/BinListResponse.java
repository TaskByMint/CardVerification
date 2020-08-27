package com.mint.CardVerification.dto.thirdParty;

import lombok.Data;

import java.lang.Number;

@Data
public class BinListResponse {

    Number number;
    String scheme;
    String type;
    String brand;
    boolean prepaid;
    Country country;
    Bank bank;
}
