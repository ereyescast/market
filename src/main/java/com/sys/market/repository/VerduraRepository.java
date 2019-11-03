package com.sys.market.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sys.market.entity.Verdura;

@Repository("verduraRepository")
public interface VerduraRepository extends JpaRepository<Verdura, Serializable>{

	public abstract Verdura findById(int id);
	public abstract Verdura findByNombre(String nombre);
	
	@Query(value = "SELECT * FROM VERDURA u WHERE u.NOMBRE in (?1) and u.PRECIO<(SELECT d.precio FROM market.verdurahist as d where d.nombre in (?2) ORDER BY id DESC LIMIT 1,1)", 
			  nativeQuery = true)
	public Verdura findOfertas(String nombre, String nombre2);
}
