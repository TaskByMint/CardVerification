package com.mint.CardVerification;

import com.mint.CardVerification.services.BinListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardVerificationApplication.class, args);
	}
@Autowired
	BinListService bl;

}
