package com.sys.market.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import com.sys.market.constant.ViewConstant;
import com.sys.market.entity.Email;
import com.sys.market.service.EmailSample;
import com.sys.market.service.FrutaService;
import com.sys.market.service.FrutahistService;
import com.sys.market.service.VerduraService;


@Controller
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class FrutaController {
	
	private static final Log LOG = LogFactory.getLog(FrutaController.class);
	
	@Autowired
	@Qualifier("verduraServiceImpl")
	private VerduraService verduraService;
	
	@Autowired
	@Qualifier("frutaServiceImpl")
	private FrutaService frutaService;
	
	@Autowired
	@Qualifier("frutahistServiceImpl")
	private FrutahistService frutahistService;
	
	@Autowired
	private EmailSample emailSample;
	
	@GetMapping("/index")
	public String showFruta(@ModelAttribute(name="correo") Email correo, Model model) {

		model.addAttribute("verdura", verduraService.listAllVerdura());
		model.addAttribute("fruta", frutaService.listAllFruta());
		model.addAttribute("ofertaFruta", frutaService.listAllOferta());
		model.addAttribute("ofertaVerdura", verduraService.listAllOferta());
		//model.addAttribute("username", user.getUsername());
		return ViewConstant.INDEX;
	}
	
	@PostMapping("/index")
	public String envioCorreo(@ModelAttribute(name="correo") Email correo) {
		
		//send a notification
		try {
			//notiticationService.sendNoification(user);
			emailSample.initiateEmailSend(correo);
		}catch(MailException e) {
		//Catch error
			LOG.info("METHOD: signupSuccess()" + e.getMessage());
		}
		
		return "redirect:/index";
	}
}
