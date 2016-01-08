package com.wavecell;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class SMSServiceImpl implements SMSService {

	private static Logger log = Logger.getLogger(SMSServiceImpl.class);

	private static final String WC_ACCOUNT_ID = "myown";
	private static final String WC_SUB_ACCOUNT_ID = "myown_hq";
	private static final String WC_PASSWORD = "bushid023";
	private static final String ENCODING = "Unicode";
	private static final String SOURCE = "wavecellTest";

	public String sendSMS(String source, String destination,
			String messageBody, String date, String time) {
		String status = null;
		try {
			if (!destination.equals(null)) {
				if (!isAlphaNumeric(destination)) {
					destination = destination.replaceAll("[-+^]*", "");
					destination = destination.trim();
				}
			}
			log.info("http://wms1.wavecell.com/Send.asmx/SendMT?AccountId="
					+ WC_ACCOUNT_ID + "&Body=" + messageBody + "&Destination="
					+ destination + "&Encoding=" + ENCODING + "&Password="
					+ WC_PASSWORD + "&ScheduledDateTime=&Source=" + SOURCE
					+ "&SubAccountId=" + WC_SUB_ACCOUNT_ID + "&UMID=");
			HttpResponse<String> response = Unirest.get(
					"http://wms1.wavecell.com/Send.asmx/SendMT?AccountId="
							+ WC_ACCOUNT_ID + "&Body=" + messageBody.trim()
							+ "&Destination=" + destination + "&Encoding="
							+ ENCODING + "&Password=" + WC_PASSWORD
							+ "&ScheduledDateTime=&Source=" + SOURCE
							+ "&SubAccountId=" + WC_SUB_ACCOUNT_ID + "&UMID=")
					.asString();
			return status = response.getStatusText();
		} catch (UnirestException e) {
			log.info("UnirestException!", e);
			return status = e.getMessage();
		} catch (Exception e) {
			log.error("Exception!", e);
			return status = e.getMessage();
		}
	}

	private static void validate(String source, String messageBody)
			throws Exception {
		if (StringUtils.isEmpty(source)) {
			throw new Exception("Empty source!");
		}

		if (StringUtils.isEmpty(messageBody)) {
			throw new Exception("Empty message body!");
		}
	}

	private static boolean isAlphaNumeric(String s) {
		String pattern = "^[a-zA-Z0-9]*$";
		if (s.matches(pattern)) {
			return true;
		}
		return false;
	}

}
