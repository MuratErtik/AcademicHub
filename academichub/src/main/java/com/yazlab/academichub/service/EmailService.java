package com.yazlab.academichub.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void afterTheRegisteration(String userEmail,String name,String lastname, String subject, String text) throws MessagingException{

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,"utf-8");
            messageHelper.setSubject(subject);
            messageHelper.setText(text);
            messageHelper.setTo(userEmail);

            javaMailSender.send(mimeMessage);
        } catch (MailException e) {
            throw new MailSendException("failed to send mail");
        }

    }

    public void completeJobOffer(String userEmail,String title , String subject, String text) throws MessagingException{

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,"utf-8");
            messageHelper.setSubject(subject);
            messageHelper.setText(text);
            messageHelper.setTo(userEmail);

            javaMailSender.send(mimeMessage);
        } catch (MailException e) {
            throw new MailSendException("failed to send mail");
        }

    }

    public void addJury(String userEmail,String name ,String lastname, String subject, String text) throws MessagingException{

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,"utf-8");
            messageHelper.setSubject(subject);
            messageHelper.setText(text);
            messageHelper.setTo(userEmail);

            javaMailSender.send(mimeMessage);
        } catch (MailException e) {
            throw new MailSendException("failed to send mail");
        }

    }

    public void completeEvaluation(String userEmail,String name ,String lastname, String subject, String text) throws MessagingException{

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,"utf-8");
            messageHelper.setSubject(subject);
            messageHelper.setText(text);
            messageHelper.setTo(userEmail);

            javaMailSender.send(mimeMessage);
        } catch (MailException e) {
            throw new MailSendException("failed to send mail");
        }

    }
}

//juri tamamlayinca gelsin 