package com.projeto.apply.domain;

import com.projeto.apply.domain.enums.Perfil;
import com.projeto.apply.domain.enums.Sexo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;

@SpringBootTest
class PessoaFisicaTest {

    @Test
    public void deveCriarUmaPessoaFisicaAPartirDoBuilder() {
        LocalDate dataNascimento = LocalDate.of(2020, 2, 2);

        PessoaFisica pessoaFisica = PessoaFisica.Builder.create()
                .nome("Nome 1")
                .cpf("12344567890")
                .email("teste@gmail.com")
                .dataNascimento(dataNascimento)
                .nacionalidade("Brasileira")
                .naturalidade("Criciúma")
                .sexo(Sexo.FEMININO)
                .senha("123")
                .perfis(Collections.singleton(Perfil.ADMIN))
                .build();

        Assertions.assertEquals("Nome 1", pessoaFisica.getNome());
        Assertions.assertEquals("12344567890", pessoaFisica.getCpf());
        Assertions.assertEquals("teste@gmail.com", pessoaFisica.getEmail());
        Assertions.assertEquals(dataNascimento, pessoaFisica.getDataNascimento());
        Assertions.assertEquals("Brasileira", pessoaFisica.getNacionalidade());
        Assertions.assertEquals("Criciúma", pessoaFisica.getNaturalidade());
        Assertions.assertEquals(Sexo.FEMININO, pessoaFisica.getSexo());
        Assertions.assertEquals(Collections.singleton(Perfil.ADMIN), pessoaFisica.getPerfis());
    }

}