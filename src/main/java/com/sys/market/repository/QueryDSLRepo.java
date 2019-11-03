package com.sys.market.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQuery;
import com.sys.market.entity.Fruta;
import com.sys.market.entity.QFruta;
import com.sys.market.entity.QVerdura;
import com.sys.market.entity.Verdura;

@Repository("queryDslRepo")
public class QueryDSLRepo {
	private QVerdura qVerdura = QVerdura.verdura;
	private QFruta qFruta = QFruta.fruta;
	@PersistenceContext
	private EntityManager em;
	 
	public List<Verdura> listadoVerduras(){
		JPAQuery<Verdura> query = new JPAQuery<Verdura>(em);
		List<Verdura> verdura = query.select(qVerdura).from(qVerdura).orderBy(qVerdura.id.asc()).fetch();
		return verdura;
	}
	  
	public List<Fruta> listadoFrutas(){
		JPAQuery<Fruta> query = new JPAQuery<Fruta>(em);
		List<Fruta> fruta = query.select(qFruta).from(qFruta).orderBy(qFruta.id.asc()).fetch();
		return fruta;	
	}
	 
	
}
