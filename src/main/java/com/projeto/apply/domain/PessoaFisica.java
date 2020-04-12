package com.projeto.apply.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.apply.domain.enums.Perfil;
import com.projeto.apply.domain.enums.Sexo;
import com.projeto.apply.domain.pattern.AbstractEntity;
import com.projeto.apply.domain.pattern.EntityBuilder;
import com.projeto.apply.domain.validation.PessoaFisicaValid;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@PessoaFisicaValid
@SequenceGenerator(name = "SEQ_PESSOAS_FISICAS", sequenceName = "SEQ_PESSOAS_FISICAS", allocationSize = 1)
public class PessoaFisica extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PESSOAS_FISICAS")
    private Integer id;

    @NotEmpty(message = "Preenchimento do nome obrigatório")
    @Length(min = 3, max = 120, message = "O nome deve ter entre {min} e {max} caracteres")
    private String nome;

    private Sexo sexo;

    @Email(message = "E-mail inválido")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Preenchimento da data de nascimento obrigatório")
    private LocalDate dataNascimento;

    @Length(max = 50, message = "O campo naturalidade deve ter no máximo {max} caracteres")
    private String naturalidade;

    @Length(max = 50, message = "O campo nacionalidade deve ter entre {min} e {max} caracteres")
    private String nacionalidade;

    @CPF
    @NotEmpty(message = "Preenchimento do CPF é obrigatório")
    private String cpf;

    @NotEmpty(message = "Preenchimento da senha obrigatória")
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis")
    private Set<Integer> perfis = new HashSet<>();

    @Override
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public static final class Builder extends EntityBuilder<PessoaFisica> {

        public Builder(PessoaFisica entity, EntityBuilder.State state) {
            super(entity, state);
        }

        public static Builder create() {
            return new Builder(new PessoaFisica(), State.NEW);
        }

        public static Builder from(PessoaFisica entity) {
            return new Builder(entity, State.BUILT);
        }

        public Builder nome(String nome) {
            entity.nome = nome;
            return this;
        }

        public Builder sexo(Sexo sexo) {
            entity.sexo = sexo;
            return this;
        }

        public Builder email(String email) {
            entity.email = email;
            return this;
        }

        public Builder dataNascimento(LocalDate dataNascimento) {
            entity.dataNascimento = dataNascimento;
            return this;
        }

        public Builder naturalidade(String naturalidade) {
            entity.naturalidade = naturalidade;
            return this;
        }

        public Builder nacionalidade(String nacionalidade) {
            entity.nacionalidade = nacionalidade;
            return this;
        }

        public Builder cpf(String cpf) {
            entity.cpf = cpf;
            return this;
        }

        public Builder senha(String senha) {
            BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
            entity.senha = entity.id == null ? pe.encode(senha) : senha;
            return this;
        }

        public Builder perfis(Set<Perfil> perfis) {
            List<Integer> perfisId = perfis.stream().map(Perfil::getId).collect(Collectors.toList());
            entity.perfis.addAll(perfisId);
            return this;
        }
    }

}
