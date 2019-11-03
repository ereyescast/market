package com.sys.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sys.market.component.VerdurahistConverter;
import com.sys.market.entity.VerduraHist;
import com.sys.market.model.VerdurahistModel;
import com.sys.market.repository.VerdurahistRepository;
import com.sys.market.service.VerdurahistService;

@Service("verdurahistServiceImpl")
public class VerdurahistServiceImpl implements VerdurahistService{
	//private static final Log LOG = LogFactory.getLog(FrutahistServiceImpl.class);

		@Autowired
		@Qualifier("verdurahistRepository")
		private VerdurahistRepository verdurahistRepository;

		
		@Autowired
		@Qualifier("verdurahistConverter")
		private VerdurahistConverter verdurahistConverter;
		
		@Override
		public VerdurahistModel addContact(VerdurahistModel verdurahistModel) {
			VerduraHist verdurahist = verdurahistRepository.save(verdurahistConverter.convertVerdurahistModel2Verdurahist(verdurahistModel));
			return verdurahistConverter.convertVerdurahist2VerdurahistModel(verdurahist);
		}

		@Override
		public VerdurahistModel updContact(VerdurahistModel verdurahistModel) {
			VerduraHist verdurahist = verdurahistRepository.save(verdurahistConverter.convertVerdurahistModel2Verdurahist(verdurahistModel));
			return verdurahistConverter.convertVerdurahist2VerdurahistModel(verdurahist);
		}
		
		@Override
		public VerduraHist findVerduraHistById(int id) {
			return verdurahistRepository.findById(id);
			
		}
		
		
		
		@Override
		public VerdurahistModel findFrutaHistByIdModel(int id) {
			return verdurahistConverter.convertVerdurahist2VerdurahistModel(findVerduraHistById(id));
			
		}

		@Override
		public void removeVerduraHist(int id) {
			VerduraHist verdurahist = findVerduraHistById(id);
			if(null!=verdurahist) {
				verdurahistRepository.delete(verdurahist);
			}
		}
}
