package com.sys.market.service;

import java.util.List;

import com.sys.market.entity.FrutaHist;
import com.sys.market.model.FrutahistModel;

public interface FrutahistService {

	public abstract FrutahistModel addContact(FrutahistModel frutahistModel);

	public abstract FrutahistModel updContact(FrutahistModel frutahistModel);

	public abstract FrutaHist findFrutaHistById(int id);

	public abstract FrutahistModel findFrutaHistByIdModel(int id);

	public abstract void removeFrutaHist(int id);

	List<FrutahistModel> listAllFruta();



}
