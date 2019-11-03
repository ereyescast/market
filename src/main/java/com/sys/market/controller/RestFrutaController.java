package com.sys.market.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sys.market.entity.Fruta;
import com.sys.market.model.FrutaModel;
import com.sys.market.model.FrutahistModel;
import com.sys.market.model.Respuesta;
import com.sys.market.repository.FrutaRepository;
import com.sys.market.repository.QueryDSLRepo;
import com.sys.market.service.FrutaService;
import com.sys.market.service.FrutahistService;


@RestController
//@CrossOrigin(origins="*")
@RequestMapping("/fruta")
public class RestFrutaController {

	private static final Log LOG = LogFactory.getLog(RestFrutaController.class);

	@Autowired
	@Qualifier("frutaServiceImpl")
	private FrutaService frutaService;
	
	@Autowired
	@Qualifier("frutahistServiceImpl")
	private FrutahistService frutaHistService;
	
	@Autowired
	@Qualifier("frutaRepository")
	private FrutaRepository frutaRepository;
	
	@Autowired
	@Qualifier("queryDslRepo")
	private QueryDSLRepo queryDslRepo;
	
	//@CrossOrigin(origins = "*", allowedHeaders = "*")

	@GetMapping(value = "/download")
    public ResponseEntity<Object> excelCustomersReport() throws IOException {
		
		try {
			  List<Fruta> customers = (List<Fruta>) frutaRepository.findAll();
			//Create blank workbook
			  String[] COLUMNs = {"Nº","Nombre", "Precio"};
		      XSSFWorkbook workbook = new XSSFWorkbook(); 

		      //Create a blank sheet
		      XSSFSheet spreadsheet = workbook.createSheet("Listado de Frutas");
		      //int width = 20; // Where width is number of caracters 
		      //spreadsheet.setDefaultColumnWidth(width);
		      //Create row object
		      XSSFRow row = null;
		   
		      	Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setColor(IndexedColors.BLUE.getIndex());
		 
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headerFont);
			
				row = spreadsheet.createRow(0);
			
				// Header
				for (int col = 0; col < COLUMNs.length; col++) {
					Cell cell = row.createCell(col);
					cell.setCellValue(COLUMNs[col]);
					cell.setCellStyle(headerCellStyle);
			
				}
				
				
				int rowIdx = 1;
				for (Fruta fruta : customers) {
					
					row = spreadsheet.createRow(rowIdx++);
					row.createCell(0).setCellValue(String.valueOf(rowIdx-1));
					row.createCell(1).setCellValue(fruta.getNombre());
					row.createCell(2).setCellValue(fruta.getPrecio());
					//row.createCell(1).setCellValue(String.valueOf(fruta.getPrecio()));
				}
				
			
				
				for (int col = 0; col < COLUMNs.length; col++) {
					spreadsheet.autoSizeColumn(col); 
			
				}
			
		

			String filename = "Frutas.xlsx";
			FileOutputStream out = new FileOutputStream(new File(filename));
			workbook.write(out);
		
			workbook.close();
			out.close();
			out.flush();
			
						
			File file = new File(filename);
			
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");
			
			ResponseEntity<Object> responseEntity = ResponseEntity.ok()
					.headers(headers)
					.contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/txt"))
					.body(resource);
			return responseEntity;
			
		}catch (Exception e ) {
			System.out.println(e);
			return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);	
		} 
		
	}
	
	@GetMapping(value="/listaFruta")
	public ResponseEntity<Object> listaFruta() {
		/*
		Respuesta respuesta = new Respuesta(true, frutaService.listAllFruta());
		*/
		
		HttpStatus status = HttpStatus.OK;
		LOG.info("Listado de Frutas");
		return new ResponseEntity<Object>(frutaService.listAllFruta(), status);
	}
	 
	@GetMapping(value="/listaOfertas")
	public ResponseEntity<Object> listaOferta() {

		Respuesta respuesta = new Respuesta(true, frutaService.listAllOferta());
		HttpStatus status = HttpStatus.OK;
		LOG.info("Listado de Ofertas");
		return new ResponseEntity<Object>(respuesta, status);
	}
	
	@PostMapping(value="/addFruta",consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
			},
				produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	public ResponseEntity<Object> addFruta(@Valid @RequestBody FrutaModel frutaModel, BindingResult result) {
		
		/*
		Respuesta respuesta = new Respuesta();
		List<Respuesta> respuestas = new ArrayList<Respuesta>();
		*/
		if(frutaRepository.findByNombre(frutaModel.getNombre())!=null) {
			//respuestas.add(new Respuesta("Nombre", "El nombre '" + frutaModel.getNombre() + "' ya existe.", "Validación"));
			//respuesta.setRespuestas(respuestas);
			HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
			return new ResponseEntity<Object>( null, status);
		}
		
		if(result.hasErrors()){
			/*
			respuesta.setCategoria("Validación");
			for(FieldError fieldError : result.getFieldErrors()){
				respuestas.add( new Respuesta( fieldError.getField(), "El campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage(), "validacion" ) );
			}
			respuesta.setRespuestas(respuestas);
			*/
			return new ResponseEntity<Object>( null, HttpStatus.BAD_REQUEST);
		} 
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = new Date();
		String hoy = dateFormat.format(date2);
		LocalDate hoy2 = LocalDate.parse(hoy);
		
		frutaModel.setFecharegistro(hoy2);
		
		frutaService.addContact(frutaModel);
		
		LOG.info("Registro de Fruta - OK");
		FrutahistModel frutahistModel = new FrutahistModel();
		frutahistModel.setId(frutaModel.getId());
		frutahistModel.setNombre(frutaModel.getNombre());
		frutahistModel.setPrecio(frutaModel.getPrecio());
		frutahistModel.setFecharegistro(hoy2);
		frutaHistService.addContact(frutahistModel);
		LOG.info("Registro de Fruta en Histórico - OK");
		/*
	
			respuesta.setResp(true);
			respuesta.setMensaje("Se registro correctamente");
		*/
		
		HttpStatus status = HttpStatus.OK;
		return new ResponseEntity<Object>(frutaModel, status);
	}
	
	@PutMapping(value="/updFruta",consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
			},
				produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	public ResponseEntity<Object> updFruta(@Valid @RequestBody FrutaModel frutaModel, BindingResult result) {
		/*
		Respuesta respuesta = new Respuesta();
		List<Respuesta> respuestas = new ArrayList<Respuesta>();
		*/
		if(frutaModel.getId() == 0){
			//respuesta.setCategoria("integridad");
			//respuesta.setMensaje("Falta indicar el id");
			return new ResponseEntity<Object>( null, HttpStatus.BAD_REQUEST );
		}
		
		/*
		if(null!=frutaService.findFrutaById(frutaModel.getId())) {
			if(result.hasErrors()){
				respuesta.setCategoria("validacion");
				for(FieldError fieldError : result.getFieldErrors()){
					respuestas.add( new Respuesta( fieldError.getField(), "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage(), "Validacion" ) );
				}
				respuesta.setRespuestas(respuestas);
				return new ResponseEntity<Object>( respuesta, HttpStatus.BAD_REQUEST );
			}
*/

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date2 = new Date();
			String hoy = dateFormat.format(date2);
			LocalDate hoy2 = LocalDate.parse(hoy);
		
			frutaModel.setFecharegistro(hoy2);
			
			frutaService.updContact(frutaModel);
			LOG.info("Actualización de datos - OK");
			FrutahistModel frutahistModel = new FrutahistModel();
			frutahistModel.setNombre(frutaModel.getNombre());
			frutahistModel.setPrecio(frutaModel.getPrecio());
			frutahistModel.setFecharegistro(hoy2);
			frutaHistService.addContact(frutahistModel);
			HttpStatus status = HttpStatus.OK;				
			
			/*
			respuesta.setResp(true);
			respuesta.setMensaje("Se modifico correctamente");
			*/
			
			return new ResponseEntity<Object>( frutaModel, status );
	/*		
		}else {
			respuesta.setCategoria("integridad");
			respuesta.setMensaje("El id no existe o no es válido");
			return new ResponseEntity<Object>( respuesta, HttpStatus.BAD_REQUEST );
		}
		*/
	}
	
	@DeleteMapping(value="delFruta/{id}")
	public ResponseEntity<Object> delFruta(@PathVariable  int id) {
		
		//Respuesta respuesta = new Respuesta();
		HttpStatus status = HttpStatus.OK;
		

			
		Fruta fruta = frutaService.findFrutaById(id);
		if(fruta!=null){
			//respuesta.setResp(true);
			frutaService.removeFruta(id);
			//respuesta.setMensaje("Se eliminó correctamente");
		}else{
			//respuesta.setCategoria("integridad");
			//respuesta.setMensaje("No existe fruta");
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<Object>( null, status );
	}
}
