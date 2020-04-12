package com.projeto.apply.services.utils;

import com.projeto.apply.domain.PessoaFisica;
import com.projeto.apply.repositories.PessoaFisicaRepository;
import com.projeto.apply.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		PessoaFisica pessoaFisica = pessoaFisicaRepository.findByEmail(email);
		if (pessoaFisica == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(pessoaFisica.getId(), pessoaFisica.getEmail(), pessoaFisica.getSenha(), pessoaFisica.getPerfis());
	}
}
