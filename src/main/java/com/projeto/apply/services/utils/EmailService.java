package com.projeto.apply.services.utils;

import com.projeto.apply.domain.PessoaFisica;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(PessoaFisica pessoaFisica, String newPass);
}
