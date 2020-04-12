package com.projeto.apply.services;

import com.projeto.apply.domain.PessoaFisica;
import com.projeto.apply.domain.enums.Perfil;
import com.projeto.apply.repositories.PessoaFisicaRepository;
import com.projeto.apply.security.UserSS;
import com.projeto.apply.services.exceptions.AuthorizationException;
import com.projeto.apply.services.exceptions.ObjectNotFoundException;
import com.projeto.apply.services.utils.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaFisicaService {

    private final PessoaFisicaRepository repository;

    public PessoaFisicaService(PessoaFisicaRepository repository) {
        this.repository = repository;
    }

    public PessoaFisica find(Integer id) {
        Optional<PessoaFisica> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PessoaFisica.class.getName()));
    }

    public PessoaFisica findByEmail(String email) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }

        PessoaFisica obj = repository.findByEmail(email);
        if (obj == null) {
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + PessoaFisica.class.getName());
        }
        return obj;
    }

    public PessoaFisica update(PessoaFisica pessoaFisica, Integer id) {
        PessoaFisica entity = find(id);

        PessoaFisica build = PessoaFisica.Builder.from(entity)
                .nome(pessoaFisica.getNome())
                .email(pessoaFisica.getEmail())
                .cpf(pessoaFisica.getCpf())
                .dataNascimento(pessoaFisica.getDataNascimento())
                .nacionalidade(pessoaFisica.getNacionalidade())
                .naturalidade(pessoaFisica.getNaturalidade())
                .sexo(pessoaFisica.getSexo())
                .senha(pessoaFisica.getSenha())
                .perfis(pessoaFisica.getPerfis())
                .build();

        return repository.save(build);
    }
}
