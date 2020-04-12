package com.projeto.apply.services.utils;

import com.projeto.apply.domain.PessoaFisica;
import com.projeto.apply.repositories.PessoaFisicaRepository;
import com.projeto.apply.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmailService emailService;

    private Random rand = new Random();

    public void sendNewPassword(String email) {

        PessoaFisica pessoaFisica = pessoaFisicaRepository.findByEmail(email);
        if (pessoaFisica == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPass = newPassword();

        pessoaFisica = PessoaFisica.Builder.from(pessoaFisica)
                .senha(pe.encode(newPass))
                .build();

		pessoaFisicaRepository.save(pessoaFisica);
        emailService.sendNewPasswordEmail(pessoaFisica, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if (opt == 0) { // gera um digito
            return (char) (rand.nextInt(10) + 48);
        } else if (opt == 1) { // gera letra maiuscula
            return (char) (rand.nextInt(26) + 65);
        } else { // gera letra minuscula
            return (char) (rand.nextInt(26) + 97);
        }
    }
}
