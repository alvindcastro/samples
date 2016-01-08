package com.wavecell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
public class SMSServiceImplTest {

	@Autowired
	SMSService smsService;

	@Test
	public void should_be_able_to_send_sms() {
		String messageBody = "Test";
		String destination = "+6599999999";
		String response = smsService.sendSMS(null, destination, messageBody,
				null, null);
		Assert.hasText(response);

	}

}
