package com.sys.market.component;

import org.springframework.stereotype.Component;

import com.sys.market.entity.Fruta;
import com.sys.market.model.FrutaModel;

@Component("frutaConverter")
public class FrutaConverter {

	public Fruta convertFrutaModel2Fruta(FrutaModel frutaModel) {
		Fruta fruta = new Fruta();
		fruta.setId(frutaModel.getId());
		fruta.setNombre(frutaModel.getNombre());
		fruta.setPrecio(frutaModel.getPrecio());
		fruta.setFecharegistro(frutaModel.getFecharegistro());
		return fruta;
	}
	
	public FrutaModel convertFruta2FrutaModel(Fruta fruta) {
		FrutaModel frutaModel = new FrutaModel();
		frutaModel.setId(fruta.getId());
		frutaModel.setNombre(fruta.getNombre());
		frutaModel.setPrecio(fruta.getPrecio());
		frutaModel.setFecharegistro(fruta.getFecharegistro());
		return frutaModel;
	}
	
}
