package com.mint.CardVerification.dto.thirdParty;

import lombok.Data;

@Data
public class Country {
     String numeric;
    String alpha2;
    String name;
    String emoji;
    String currency;
    String latitude;
    String longitude;
}
