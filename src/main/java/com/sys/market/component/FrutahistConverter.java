package com.sys.market.component;

import org.springframework.stereotype.Component;

import com.sys.market.entity.FrutaHist;
import com.sys.market.model.FrutahistModel;



@Component("frutahistConverter")
public class FrutahistConverter {
	public FrutaHist convertFrutahistModel2Frutahist(FrutahistModel frutahistModel) {
		FrutaHist frutahist = new FrutaHist();
		frutahist.setId(frutahistModel.getId());
		frutahist.setNombre(frutahistModel.getNombre());
		frutahist.setPrecio(frutahistModel.getPrecio());
		frutahist.setFecharegistro(frutahistModel.getFecharegistro());
		return frutahist;
	}
	
	public FrutahistModel convertFrutahist2FrutahistModel(FrutaHist frutahist) {
		FrutahistModel frutahistModel = new FrutahistModel();
		frutahistModel.setId(frutahist.getId());
		frutahistModel.setNombre(frutahist.getNombre());
		frutahistModel.setPrecio(frutahist.getPrecio());
		frutahistModel.setFecharegistro(frutahist.getFecharegistro());
		return frutahistModel;
	}
}
