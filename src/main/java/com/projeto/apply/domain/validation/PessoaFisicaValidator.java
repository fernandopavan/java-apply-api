package com.projeto.apply.domain.validation;

import com.projeto.apply.domain.PessoaFisica;
import com.projeto.apply.repositories.PessoaFisicaRepository;
import com.projeto.apply.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class PessoaFisicaValidator implements ConstraintValidator<PessoaFisicaValid, PessoaFisica> {

    @Autowired
    private PessoaFisicaRepository repo;

    @Override
    public void initialize(PessoaFisicaValid ann) {
    }

    @Override
    public boolean isValid(PessoaFisica pessoaFisica, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        //TODO Rever: vem nulo ao rodar o DBService.class
        if (repo == null) {
            return true;
        }

        Integer id = pessoaFisica.getId();

        list.addAll(existsCpf(list, id, pessoaFisica.getCpf()));
        list.addAll(existsEmail(list, id, pessoaFisica.getEmail()));

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

    private List<FieldMessage> existsCpf(List<FieldMessage> list, Integer id, String cpf) {
        PessoaFisica existsCpf = repo.findByCpf(cpf);

        if ((id == null && existsCpf != null) || (id != null && existsCpf != null && !id.equals(existsCpf.getId()))) {
            list.add(new FieldMessage("cpf", "CPF já existente"));
        }

        return list;
    }

    private List<FieldMessage> existsEmail(List<FieldMessage> list, Integer id, String email) {
        PessoaFisica existsEmail = repo.findByEmail(email);

        if ((id == null && existsEmail != null) || (id != null && existsEmail != null && !id.equals(existsEmail.getId()))) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        return list;
    }
}

