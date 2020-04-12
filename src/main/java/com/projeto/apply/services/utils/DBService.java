package com.projeto.apply.services.utils;

import com.projeto.apply.domain.PessoaFisica;
import com.projeto.apply.domain.enums.Perfil;
import com.projeto.apply.domain.enums.Sexo;
import com.projeto.apply.repositories.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;

@Service
public class DBService {

    @Autowired
    private PessoaFisicaRepository repository;

    public void instantiateTestDatabase() {
        PessoaFisica pessoaFisica = PessoaFisica.Builder.create()
                .nome("Maria de Aparecida")
                .email("maria@gmail.com")
                .cpf("09332261954")
                .dataNascimento(LocalDate.of(2010, 1, 1))
                .nacionalidade("Brasileira")
                .naturalidade("Criciúma")
                .sexo(Sexo.FEMININO)
                .senha("123")
                .perfis(Collections.singleton(Perfil.ADMIN))
                .build();

        repository.save(pessoaFisica);
    }
}
