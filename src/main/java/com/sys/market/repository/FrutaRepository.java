package com.sys.market.repository;

import java.io.Serializable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sys.market.entity.Fruta;

@Repository("frutaRepository")
public interface FrutaRepository extends JpaRepository<Fruta, Serializable>{

	public abstract Fruta findById(int id);
	public abstract Fruta findByNombre(String nombre);
	
	
	@Query(value = "SELECT * FROM market.fruta u WHERE u.NOMBRE in (?1) and u.PRECIO<(SELECT d.precio FROM market.frutaHist as d where d.nombre in (?2) ORDER BY id DESC LIMIT 1,1)", 
			  nativeQuery = true)
	public Fruta findOfertas(String nombre, String nombre2);
}
