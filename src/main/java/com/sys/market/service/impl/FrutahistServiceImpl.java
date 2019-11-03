package com.sys.market.service.impl;


import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sys.market.component.FrutahistConverter;
import com.sys.market.entity.FrutaHist;
import com.sys.market.model.FrutahistModel;
import com.sys.market.repository.FrutahistRepository;
import com.sys.market.repository.QueryDSLRepo;
import com.sys.market.service.FrutahistService;

@Service("frutahistServiceImpl")
public class FrutahistServiceImpl implements FrutahistService {

	//private static final Log LOG = LogFactory.getLog(FrutahistServiceImpl.class);

	@Autowired
	@Qualifier("frutahistRepository")
	private FrutahistRepository frutahistRepository;

	
	@Autowired
	@Qualifier("frutahistConverter")
	private FrutahistConverter frutahistConverter;
	
	
	@Autowired
	@Qualifier("queryDslRepo")
	private QueryDSLRepo queryDslRepo;
	
	@Override
	public FrutahistModel addContact(FrutahistModel frutahistModel) {
		FrutaHist frutahist = frutahistRepository.save(frutahistConverter.convertFrutahistModel2Frutahist(frutahistModel));
		return frutahistConverter.convertFrutahist2FrutahistModel(frutahist);
	}

	@Override
	public FrutahistModel updContact(FrutahistModel frutahistModel) {
		FrutaHist frutahist = frutahistRepository.save(frutahistConverter.convertFrutahistModel2Frutahist(frutahistModel));
		return frutahistConverter.convertFrutahist2FrutahistModel(frutahist);
	}
	
	@Override
	public List<FrutahistModel> listAllFruta() {
		List<FrutaHist> frutas = frutahistRepository.findAll();
		List<FrutahistModel> frutaHistModel = new ArrayList<FrutahistModel>();
		for(FrutaHist frutaHist : frutas) {
			frutaHistModel.add(frutahistConverter.convertFrutahist2FrutahistModel(frutaHist));
		}
		return frutaHistModel;
	}
	
	@Override
	public FrutaHist findFrutaHistById(int id) {
		return frutahistRepository.findById(id);
		
	}
	
	
	
	@Override
	public FrutahistModel findFrutaHistByIdModel(int id) {
		return frutahistConverter.convertFrutahist2FrutahistModel(findFrutaHistById(id));
		
	}

	@Override
	public void removeFrutaHist(int id) {
		FrutaHist frutaHist = findFrutaHistById(id);
		if(null!=frutaHist) {
			frutahistRepository.delete(frutaHist);
		}
	}
}
