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
        email.setTo("<MAIL@MAIL.COM>");
        email.setCc("<MAIL@MAIL.COM>");
        email.setSubject("Teste de e-mail utilizando spring-mail");
        email.setText("Enviei esse e-mail utilizando spring.");
        javaMailSender.send(email);
    }

    public void sendMainEmployee(List<Employee> employeeList) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("<MAIL@MAIL.COM>");
        email.setCc("<MAIL@MAIL.COM>");
        email.setSubject("TESTE - Automatização abertura chamado automático");

        email.setText("*****************************************************");
        email.setText(email.getText() + "\n             *** Relatório - Coleta de funcionários ***");
        email.setText(email.getText() + "\n*****************************************************\n");

        employeeList.forEach(x -> email.setText((email.getText() == null ? "" : email.getText()) + "Id: " + x.getId() + " | Name: " + x.getName() + " | Salary: " +
                x.getSalary() + " | Role: " + x.getRole() + " | Hiring Date: " + x.getHiringDate() + System.lineSeparator()));
        email.setText(email.getText() + "\n*****************************************************\n");
        javaMailSender.send(email);
    }
}
