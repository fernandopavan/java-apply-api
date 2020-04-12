package com.projeto.apply.repositories;

import com.projeto.apply.domain.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer> {

    @Transactional(readOnly = true)
    PessoaFisica findByEmail(String email);

    @Transactional(readOnly = true)
    PessoaFisica findByCpf(String cpf);

    @Transactional(readOnly = true)
    List<PessoaFisica> findAllByNomeContaining(String name);

}
