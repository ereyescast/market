package com.sys.market.component;

import org.springframework.stereotype.Component;

import com.sys.market.entity.VerduraHist;
import com.sys.market.model.VerdurahistModel;


@Component("verdurahistConverter")
public class VerdurahistConverter {
	public VerduraHist convertVerdurahistModel2Verdurahist(VerdurahistModel verdurahistModel) {
		VerduraHist verdurahist = new VerduraHist();
		verdurahist.setId(verdurahistModel.getId());
		verdurahist.setNombre(verdurahistModel.getNombre());
		verdurahist.setPrecio(verdurahistModel.getPrecio());
		verdurahist.setFecharegistro(verdurahistModel.getFecharegistro());
		return verdurahist;
	}
	
	public VerdurahistModel convertVerdurahist2VerdurahistModel(VerduraHist verduraHist) {
		VerdurahistModel verdurahistModel = new VerdurahistModel();
		verdurahistModel.setId(verduraHist.getId());
		verdurahistModel.setNombre(verduraHist.getNombre());
		verdurahistModel.setPrecio(verduraHist.getPrecio());
		verdurahistModel.setFecharegistro(verduraHist.getFecharegistro());
		return verdurahistModel;
	}
}
