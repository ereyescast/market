package com.sys.market.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sys.market.entity.Verdura;
import com.sys.market.model.Respuesta;
import com.sys.market.model.VerduraModel;
import com.sys.market.model.VerdurahistModel;
import com.sys.market.repository.QueryDSLRepo;
import com.sys.market.repository.VerduraRepository;
import com.sys.market.service.VerduraService;
import com.sys.market.service.VerdurahistService;

@RestController
@RequestMapping("/verdura")
public class RestVerduraController {
	
	private static final Log LOG = LogFactory.getLog(RestLoginController.class);
	
	@Autowired
	@Qualifier("verduraServiceImpl")
	private VerduraService verduraService;
	
	@Autowired
	@Qualifier("verdurahistServiceImpl")
	private VerdurahistService verduraHistService;
	
	@Autowired
	@Qualifier("verduraRepository")
	private VerduraRepository verduraRepository;
	
	@Autowired
	@Qualifier("queryDslRepo")
	private QueryDSLRepo queryDslRepo;
	
	@GetMapping(value="/listaVerdura")
	public ResponseEntity<Object> listaVerdura() {
		Respuesta respuesta = new Respuesta(true, null, verduraService.listAllVerdura());
		HttpStatus status = HttpStatus.OK;
		LOG.info("Listado de Frutas");
		return new ResponseEntity<Object>(respuesta, status);
	}
	
	@GetMapping(value="/listaOfertas")
	public ResponseEntity<Object> listaOferta() {

		Respuesta respuesta = new Respuesta(true, null, verduraService.listAllOferta());
		HttpStatus status = HttpStatus.OK;
		LOG.info("Listado de Ofertas");
		return new ResponseEntity<Object>(respuesta, status);
	}
	
	@PostMapping(value="/addVerdura",consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
			},
				produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	public ResponseEntity<Object> addVerdura(@Valid @RequestBody VerduraModel verduraModel, BindingResult result) {
		
		Respuesta respuesta = new Respuesta();
		List<Respuesta> respuestas = new ArrayList<Respuesta>();
		
		if(verduraRepository.findByNombre(verduraModel.getNombre())!=null) {
			respuestas.add(new Respuesta("Nombre", "El nombre '" + verduraModel.getNombre() + "' ya existe.", "Validación"));
			respuesta.setRespuestas(respuestas);
			HttpStatus status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Object>( respuesta, status);
		}
		
		if(result.hasErrors()){
			respuesta.setCategoria("Validación");
			for(FieldError fieldError : result.getFieldErrors()){
				respuestas.add( new Respuesta( fieldError.getField(), "El campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage(), "validacion" ) );
			}
			respuesta.setRespuestas(respuestas);
			return new ResponseEntity<Object>( respuesta, HttpStatus.BAD_REQUEST );
		} 
				
		verduraService.addContact(verduraModel);
		
		LOG.info("Registro de Verdura - OK");
		VerdurahistModel verdurahistModel = new VerdurahistModel();
		verdurahistModel.setId(verduraModel.getId());
		verdurahistModel.setNombre(verduraModel.getNombre());
		verdurahistModel.setPrecio(verduraModel.getPrecio());
		verdurahistModel.setFecharegistro(verduraModel.getFecharegistro());
		verduraHistService.addContact(verdurahistModel);
		LOG.info("Registro de Verdura en Histórico - OK");
		
	
			respuesta.setResp(true);
			respuesta.setMensaje("Se registro correctamente");
	
		HttpStatus status = HttpStatus.OK;
		return new ResponseEntity<Object>(respuesta, status);
	}
	
	@PutMapping(value="/updVerdura",consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
			},
				produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	public ResponseEntity<Object> updVerdura(@Valid @RequestBody VerduraModel verduraModel, BindingResult result) {
		
		Respuesta respuesta = new Respuesta();
		List<Respuesta> respuestas = new ArrayList<Respuesta>();
		
		if(result.hasErrors()){
			respuesta.setCategoria("validacion");
			for(FieldError fieldError : result.getFieldErrors()){
				respuestas.add( new Respuesta(fieldError.getField(), "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage(), "Validacion" ) );
			}
			respuesta.setRespuestas(respuestas);
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST );
		}
				
		if(verduraModel.getId() == 0){
			respuesta.setCategoria("integridad");
			respuesta.setMensaje("Falta indicar el id");
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST );
		}
		

		verduraService.updContact(verduraModel);
		
		LOG.info("Actualización de datos - OK");
		VerdurahistModel verdurahistModel = new VerdurahistModel();
		verdurahistModel.setNombre(verduraModel.getNombre());
		verdurahistModel.setPrecio(verduraModel.getPrecio());
		verdurahistModel.setFecharegistro(verduraModel.getFecharegistro());
		verduraHistService.addContact(verdurahistModel);
		HttpStatus status = HttpStatus.OK;				
		respuesta.setResp(true);
		respuesta.setMensaje("Se modifico correctamente");
		
		return new ResponseEntity<Object>(respuesta, status );
	}
	
	@DeleteMapping(value="delVerdura/{id}")
	public ResponseEntity<Object> delVerdura(@PathVariable  int id) {
		
		Respuesta respuesta = new Respuesta();
		HttpStatus status = HttpStatus.OK;
		
			
		Verdura verdura = verduraService.findVerduraById(id);
		if(verdura!=null){
			respuesta.setResp(true);
			verduraService.removeVerdura(id);
			respuesta.setMensaje("Se eliminó correctamente");
		}else{
			respuesta.setCategoria("integridad");
			respuesta.setMensaje("No existe verdura");
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<Object>( respuesta, status );
	}
}
