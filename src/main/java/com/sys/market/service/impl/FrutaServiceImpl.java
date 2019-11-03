package com.sys.market.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sys.market.component.FrutaConverter;
import com.sys.market.entity.Fruta;
import com.sys.market.model.FrutaModel;
import com.sys.market.repository.FrutaRepository;
import com.sys.market.repository.QueryDSLRepo;
import com.sys.market.service.FrutaService;

@Service("frutaServiceImpl")
public class FrutaServiceImpl implements FrutaService {
		private static final Log LOG = LogFactory.getLog(FrutahistServiceImpl.class);

		@Autowired
		@Qualifier("frutaRepository")
		private FrutaRepository frutaRepository;

		
		@Autowired
		@Qualifier("frutaConverter")
		private FrutaConverter frutaConverter;
		
		@Autowired
		@Qualifier("queryDslRepo")
		private QueryDSLRepo queryDslRepo;
		
		@Override
		public FrutaModel addContact(FrutaModel frutaModel) {
			Fruta fruta = frutaRepository.save(frutaConverter.convertFrutaModel2Fruta(frutaModel));
			return frutaConverter.convertFruta2FrutaModel(fruta);
		}

		@Override
		public FrutaModel updContact(FrutaModel frutaModel) {
			Fruta fruta = frutaRepository.save(frutaConverter.convertFrutaModel2Fruta(frutaModel));
			return frutaConverter.convertFruta2FrutaModel(fruta);
		}
		@Override
		public List<FrutaModel> listAllFruta() {
			List<Fruta> frutas = frutaRepository.findAll(); 
			//List<Fruta> frutas = frutaRepository.findAll();
			List<FrutaModel> frutaModel = new ArrayList<FrutaModel>();
			for(Fruta fruta : frutas) {
				frutaModel.add(frutaConverter.convertFruta2FrutaModel(fruta));
			}
			return frutaModel;
		}
		
		@Override
		public List<FrutaModel> listAllOferta() {
			List<FrutaModel> frutaModel = new ArrayList<>();
			List<Fruta> fruta = frutaRepository.findAll(); 
			List<Fruta> fruta2 = new ArrayList<Fruta>();
			for(Fruta frut: fruta) {
				Fruta eva = frutaRepository.findOfertas(frut.getNombre(),frut.getNombre());
				if(eva==null) {
					LOG.info("Eliminando valores nulos");
				}else {
					fruta2.add(eva);
				}
				
			}
			for(Fruta fruta5 : fruta2) {
			frutaModel.add(frutaConverter.convertFruta2FrutaModel(fruta5));
			}
			return frutaModel;
			
		}
	
		
		@Override
		public Fruta findFrutaById(int id) {
			return frutaRepository.findById(id);
			
		}
		
		
		
		@Override
		public FrutaModel findFrutaByIdModel(int id) {
			return frutaConverter.convertFruta2FrutaModel(findFrutaById(id));
			
		}

		@Override
		public void removeFruta(int id) {
			Fruta fruta = findFrutaById(id);
			if(null!=fruta) {
				frutaRepository.delete(fruta);
			}
		}
}
