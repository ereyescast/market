package com.sys.market.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sys.market.entity.Usuario;



@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable>{
	public abstract List<Usuario> findByUsernameAndPassword(String username, String password);
	public abstract Usuario findByUsername(String username);
	public abstract Usuario findById(int id);

}
