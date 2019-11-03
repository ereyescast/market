package com.sys.market.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sys.market.entity.Usuario;
import com.sys.market.repository.UsuarioRepository;
import com.sys.market.service.LoginService;

@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;
	
	@Override
	public boolean login(String correo, String pass) {
		//System.out.println("In Check login");
		//Session session = sessionFactory.openSession();
		boolean userFound = false;
		//Query using Hibernate Query Language
		//String SQL_QUERY ="from Persona as c where c.correo =? and c.pass =?";
		//Query query = session.createQuery(SQL_QUERY);
		//System.out.println(query);
		//query.setParameter(0,correo);
		//query.setParameter(1,pass);
		List<Usuario> list = usuarioRepository.findByUsernameAndPassword(correo, pass);
		//System.out.println(list);
		if ((list != null) && (list.size() > 0))  {
			userFound= true;
		}
		//session.close();
		return userFound;     		
	}
}
