package com.sys.market.component;

import org.springframework.stereotype.Component;

import com.sys.market.entity.Verdura;
import com.sys.market.model.VerduraModel;

@Component("verduraConverter")
public class VerduraConverter {

	public Verdura convertVerduraModel2Verdura(VerduraModel verduraModel) {
		Verdura verdura = new Verdura();
		verdura.setId(verduraModel.getId());
		verdura.setNombre(verduraModel.getNombre());
		verdura.setPrecio(verduraModel.getPrecio());
		verdura.setFecharegistro(verduraModel.getFecharegistro());
		return verdura;
	}
	
	public VerduraModel convertVerdura2VerduraModel(Verdura verdura) {
		VerduraModel verduraModel = new VerduraModel();
		verduraModel.setId(verdura.getId());
		verduraModel.setNombre(verdura.getNombre());
		verduraModel.setPrecio(verdura.getPrecio());
		verduraModel.setFecharegistro(verdura.getFecharegistro());
		return verduraModel;
	}
	
}
