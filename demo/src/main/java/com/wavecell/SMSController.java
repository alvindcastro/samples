package com.wavecell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SMSController {
	
	@Autowired
	private SMSService smsService;
	
	@RequestMapping("/")
	public ModelAndView home() {
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
	public String sendSMS(@RequestParam String source, @RequestParam String destination, @RequestParam String messageBody,
			@RequestParam String messageDate,@RequestParam String messageTime, RedirectAttributes redirectAttributes) {
		String response = smsService.sendSMS(source, destination, messageBody, messageDate, messageTime);
		redirectAttributes.addFlashAttribute("message",
				String.format("Response is: ", response));
		return "redirect:/";
	}

}
