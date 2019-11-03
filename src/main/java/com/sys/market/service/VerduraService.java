package com.sys.market.service;

import java.util.List;

import com.sys.market.entity.Verdura;
import com.sys.market.model.VerduraModel;

public interface VerduraService {

	public abstract VerduraModel addContact(VerduraModel verduraModel);

	public abstract VerduraModel updContact(VerduraModel verduraModel);

	public abstract Verdura findVerduraById(int id);

	VerduraModel findFrutaByIdModel(int id);

	public abstract void removeVerdura(int id);

	public abstract List<VerduraModel> listAllVerdura();

	public abstract List<VerduraModel> listAllOferta();

}
