package com.sys.market.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sys.market.entity.VerduraHist;

@Repository("verdurahistRepository")
public interface VerdurahistRepository extends JpaRepository<VerduraHist, Serializable>{
	
	public abstract VerduraHist findById(int id);
}
