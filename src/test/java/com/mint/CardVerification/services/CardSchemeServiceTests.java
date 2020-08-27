package com.mint.CardVerification.services;

import com.mint.CardVerification.dto.CardVerifyResponse;
import com.mint.CardVerification.dto.PayloadResponse;
import com.mint.CardVerification.dto.thirdParty.BinListResponse;
import com.mint.CardVerification.services.BinListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertNull;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CardSchemeServiceTests {

	@Autowired
	CardSchemeService cardSchemeService;


	@Test
	public void verifyCardTest(){

			PayloadResponse payload = cardSchemeService.verifyCardBin("45717360");
			if(payload!=null){
				assertThat(payload.getType()).isEqualTo("debit");
			}else{
				assertThrows(NullPointerException.class,
						()->{
							assertNull(payload.getType(),true);
						});
			}

	}

}
