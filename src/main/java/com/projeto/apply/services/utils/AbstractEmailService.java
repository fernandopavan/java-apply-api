package com.projeto.apply.services.utils;

import com.projeto.apply.domain.PessoaFisica;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendNewPasswordEmail(PessoaFisica pessoaFisica, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(pessoaFisica, newPass);
        sendEmail(sm);
    }

    private SimpleMailMessage prepareNewPasswordEmail(PessoaFisica pessoaFisica, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pessoaFisica.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);
        return sm;
    }
}
