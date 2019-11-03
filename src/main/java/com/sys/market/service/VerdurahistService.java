package com.sys.market.service;

import com.sys.market.entity.VerduraHist;
import com.sys.market.model.VerdurahistModel;

public interface VerdurahistService {

	public abstract VerdurahistModel addContact(VerdurahistModel verdurahistModel);

	public abstract VerdurahistModel updContact(VerdurahistModel verdurahistModel);

	public abstract VerduraHist findVerduraHistById(int id);

	VerdurahistModel findFrutaHistByIdModel(int id);

	public abstract void removeVerduraHist(int id);

}
