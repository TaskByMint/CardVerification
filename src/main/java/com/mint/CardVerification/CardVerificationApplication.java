package com.mint.CardVerification;

import com.mint.CardVerification.dto.thirdParty.BinListResponse;
import com.mint.CardVerification.services.BinListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CardVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardVerificationApplication.class, args);
	}
@Autowired
	BinListService bl;

}
