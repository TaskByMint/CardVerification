package com.mint.CardVerification.dto;

import lombok.Data;
import org.json.JSONObject;

@Data
public class CounterResponse<T> {
    private boolean success;
    private int start;
    private int limit;
    private long size;
    private String payload;



    public CounterResponse() {
    }

    public CounterResponse(boolean success, int start, int limit, long size, String payload) {
        this.success = success;
        this.start = start;
        this.limit = limit;
        this.size = size;
        this.payload = payload;


    }


}
