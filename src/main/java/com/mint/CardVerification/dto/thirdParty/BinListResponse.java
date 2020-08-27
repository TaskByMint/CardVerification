package com.mint.CardVerification.dto.thirdParty;

import lombok.Data;


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
