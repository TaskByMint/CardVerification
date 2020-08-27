package com.mint.CardVerification.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "card_bin_counter", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "card_bin"
        })
})
public class CardBinCounter{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "card_bin", length = 8)
    private String cardbin;

    @Column(name = "count")
    private int count;



}
