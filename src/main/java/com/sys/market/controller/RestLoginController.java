package com.sys.market.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sys.market.model.Respuesta;
import com.sys.market.service.LoginService;

@RestController
//@CrossOrigin(origins = "http://localhost:5000")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/rest")
public class RestLoginController {

	private static final Log LOG = LogFactory.getLog(RestLoginController.class);
	
	private boolean persona;
	
	private HttpHeaders	headers;

	public RestLoginController() {
		headers = new HttpHeaders();
		headers.add( "Content-Type", "application/json; charset=utf-8" );
	}

	@Autowired
	@Qualifier("loginServiceImpl")
	private LoginService loginService;
	
	@GetMapping(value="/login/{username}/{password}")
	public ResponseEntity<Object> Login(@PathVariable String username, @PathVariable String password) {
		
		Respuesta resp = new Respuesta();
		resp.setCategoria("Login");
		HttpStatus status = HttpStatus.OK;
		
		persona = loginService.login(username, password);
		LOG.info(persona);
		if(persona==true){
			resp.setResp(true);
			resp.setMensaje("Success");
			
		}else{
			resp.setMensaje("Failed");
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<Object>(resp, headers, status);
	}
}
