package school.sptech.naumspringapi.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@email.com");
        message.setTo(email);
        message.setSubject("Agendamento Barbearia TM");
        message.setText("Agendamento realizado com sucesso\n" +
                "Por favor, comparecer no horário marcado *sem atrasos*\n" +
                "Caso não possa mais ir, desmarcar o agendamento pelo nosso site.\n" +
                "Muito Obrigado!");
        mailSender.send(message);
    }

    public void sendEmailBarbeiro(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@email.com");
        message.setTo(email);
        message.setSubject("Agendamento Barbearia TM");
        message.setText("Mais um agendamento marcado!");
        mailSender.send(message);
    }
}
