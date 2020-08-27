/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mint.CardVerification.dto;

import lombok.Data;

import java.util.List;

/**
 *
 * @author hp
 */
@Data
public class PagedResponse<T> {
    
    private boolean status;
    private T result;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public PagedResponse() {
    }

    public PagedResponse(boolean status, T result, int page, int size, long totalElements, int totalPages, boolean last) {
        this.status = status;
        this.result = result;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }
}
