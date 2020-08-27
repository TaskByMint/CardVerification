package com.mint.CardVerification.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "card_schemes")
public class CardScheme{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "card_bin", length = 8)
    private String cardbin;

    @Column(name = "success")
    private boolean success;

    @Column(name = "scheme")
    private String scheme;

    @Column(name = "type")
    private String type;

    @Column(name = "bank")
    private String bank;

    @Column(name = "bin_List_Response",columnDefinition = "LONGTEXT")
    private String binListResponse;

}
