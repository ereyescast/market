package com.sys.market.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sys.market.component.VerduraConverter;
import com.sys.market.entity.Verdura;
import com.sys.market.model.VerduraModel;
import com.sys.market.repository.QueryDSLRepo;
import com.sys.market.repository.VerduraRepository;
import com.sys.market.service.VerduraService;

@Service("verduraServiceImpl")
public class VerduraServiceImpl implements VerduraService{
			private static final Log LOG = LogFactory.getLog(VerduraServiceImpl.class);

			@Autowired
			@Qualifier("verduraRepository")
			private VerduraRepository verduraRepository;

			
			@Autowired
			@Qualifier("verduraConverter")
			private VerduraConverter verduraConverter;
			
			@Autowired
			@Qualifier("queryDslRepo")
			private QueryDSLRepo queryDslRepo;
			
			@Override
			public VerduraModel addContact(VerduraModel verduraModel) {
				Verdura verdura = verduraRepository.save(verduraConverter.convertVerduraModel2Verdura(verduraModel));
				return verduraConverter.convertVerdura2VerduraModel(verdura);
			}

			@Override
			public VerduraModel updContact(VerduraModel verduraModel) {
				Verdura verdura = verduraRepository.save(verduraConverter.convertVerduraModel2Verdura(verduraModel));
				return verduraConverter.convertVerdura2VerduraModel(verdura);
			}
			
			@Override
			public List<VerduraModel> listAllVerdura() {
				List<Verdura> verduras = queryDslRepo.listadoVerduras();
				List<VerduraModel> verduraModel = new ArrayList<VerduraModel>();
				for(Verdura verdura : verduras) {
					verduraModel.add(verduraConverter.convertVerdura2VerduraModel(verdura));
				}
				return verduraModel;
			}
			
			@Override
			public List<VerduraModel> listAllOferta() {
				List<VerduraModel> verduraModel = new ArrayList<>();
				List<Verdura> verdura = verduraRepository.findAll(); 
				List<Verdura> verdura2 = new ArrayList<Verdura>();
				for(Verdura verd: verdura) {
					Verdura eva = verduraRepository.findOfertas(verd.getNombre(),verd.getNombre());
					if(eva==null) {
						LOG.info("Eliminando valores nulos");
					}else {
						verdura2.add(eva);
					}
					
				}
				for(Verdura verdura5 : verdura2) {
				verduraModel.add(verduraConverter.convertVerdura2VerduraModel(verdura5));
				}
				return verduraModel;
				
			}
			
			@Override
			public Verdura findVerduraById(int id) {
				return verduraRepository.findById(id);
				
			}
			
			
			
			@Override
			public VerduraModel findFrutaByIdModel(int id) {
				return verduraConverter.convertVerdura2VerduraModel(findVerduraById(id));
				
			}

			@Override
			public void removeVerdura(int id) {
				Verdura verdura = findVerduraById(id);
				if(null!=verdura) {
					verduraRepository.delete(verdura);
				}
			}
}
