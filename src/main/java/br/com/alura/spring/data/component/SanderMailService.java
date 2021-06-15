package br.com.alura.spring.data.component;

import br.com.alura.spring.data.orm.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SanderMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send() {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("gustavo.araujo@somosagility.com.br");
        email.setCc("suporte@somosagility.com.br");
        email.setSubject("Teste de e-mail utilizando spring-mail");
        email.setText("Enviei esse e-mail utilizando spring.");
        javaMailSender.send(email);
    }

    public void sendMainEmployee(List<Employee> employeeList) {
        SimpleMailMessage email = new SimpleMailMessage();



        javaMailSender.send(email);
    }
}
