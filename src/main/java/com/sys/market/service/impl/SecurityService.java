package com.sys.market.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sys.market.entity.Usuario;
import com.sys.market.repository.UsuarioRepository;



@Service("usuarioService")
public class SecurityService implements UserDetailsService {

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getRole());
		return buildUser(user, authorities);
	}
	
	private User buildUser(Usuario user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildAuthorities(String userRoles){
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		
	
			auths.add(new SimpleGrantedAuthority(userRoles));
	
		
		return new ArrayList<GrantedAuthority>(auths);
		
	}

}
