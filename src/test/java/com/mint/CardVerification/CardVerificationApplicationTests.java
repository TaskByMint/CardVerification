package com.mint.CardVerification;

import com.mint.CardVerification.dto.thirdParty.BinListResponse;
import com.mint.CardVerification.services.BinListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.*;


@SpringBootTest
class CardVerificationApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	BinListService bl;

	@Test
	void checkThirdPartyApiIsReturningValue(){

					BinListResponse response = bl.getBinList("45717360");
					assertNotNull(response.getScheme(),true);
	}

	@Test
	void checkThirdPartyApiIsReturningNull(){

		assertThrows(NullPointerException.class,
				()->{
					BinListResponse response = bl.getBinList("123456");
					assertNull(response.getScheme(),true);
				});
	}


}
