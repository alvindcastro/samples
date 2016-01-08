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
	public String sendSMS(@RequestParam Long paymentInstrumentId, RedirectAttributes redirectAttributes) {
		smsService.sendSMS();
		redirectAttributes.addFlashAttribute("message",
				String.format("Payment instrument %s locked.", paymentInstrumentId));
		return "redirect:/";
	}

}
