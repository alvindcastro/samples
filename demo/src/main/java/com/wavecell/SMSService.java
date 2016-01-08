package com.wavecell;

public interface SMSService {
	public String sendSMS(String source, String destination, String messageBody, String date, String time);
}
