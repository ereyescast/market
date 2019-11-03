package com.sys.market.service;

import java.util.List;

import com.sys.market.entity.Fruta;
import com.sys.market.model.FrutaModel;

public interface FrutaService {

	public abstract FrutaModel addContact(FrutaModel frutaModel);

	public abstract FrutaModel updContact(FrutaModel frutaModel);

	public abstract Fruta findFrutaById(int id);

	FrutaModel findFrutaByIdModel(int id);

	public abstract void removeFruta(int id);

	public abstract List<FrutaModel> listAllFruta();

	public abstract List<FrutaModel> listAllOferta();


}
