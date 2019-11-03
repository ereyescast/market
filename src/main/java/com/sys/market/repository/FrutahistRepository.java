package com.sys.market.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sys.market.entity.FrutaHist;

@Repository("frutahistRepository")
public interface FrutahistRepository extends JpaRepository<FrutaHist, Serializable> {
	
	public abstract FrutaHist findById(int id);
}
