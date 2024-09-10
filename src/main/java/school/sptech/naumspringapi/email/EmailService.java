package school.sptech.naumspringapi.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class EmailService {
    private JavaMailSender mailSender;
    private String texto;

    List<AgendamentoListagemDto> agendamentoListagemDtos = new ArrayList<>();
    FilaObj<AgendamentoListagemDto> filaObj = new FilaObj<>(30);

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void colocarLista(AgendamentoListagemDto agendamentoListagemDto) {
        agendamentoListagemDtos.add(agendamentoListagemDto);
    }

    public void enviaFila() {
        LocalDate dataAtual = LocalDate.now();

        agendamentoListagemDtos.sort((a, b) -> a.getDataHoraAgendamento().compareTo(b.getDataHoraAgendamento()));

        for (int i = 0; i < agendamentoListagemDtos.size(); i++) {
            if (filaObj.isFull()) {
                throw new IllegalStateException("A fila está cheia.");
            } else {
                LocalDateTime dataHora = agendamentoListagemDtos.get(i).getDataHoraAgendamento();
                LocalDate dataAgendamento = dataHora.toLocalDate();
                if (dataAtual.equals(dataAgendamento)) {
                    System.out.println(agendamentoListagemDtos.get(i));
                    filaObj.insert(agendamentoListagemDtos.get(i));
                    agendamentoListagemDtos.remove(i);
                    i--;
                }
            }
        }
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

    public void sendEmailCancelamento(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@email.com");
        message.setTo(email);
        message.setSubject("Agendamento Barbearia TM");
        message.setText("Agendamento Cancelado\n" +
                "O Barbeiro cancelou seu Agendamento por conta de um imprevisto\n" +
                "Caso ainda pretenda ir, marcar um novo agendamento pelo nosso site.\n" +
                "Muito Obrigado!");
        mailSender.send(message);
    }

    public void sendEmailDia() {
        enviaFila();
        LocalDate dataAtual = LocalDate.now();

        if (!filaObj.isEmpty()) {
            LocalDateTime dataHora = filaObj.peek().getDataHoraAgendamento();
            LocalDate dataAgendamento = dataHora.toLocalDate();

            if (dataAtual.equals(dataAgendamento)) {
                String email = filaObj.peek().getCliente().getEmail();
                String text = String.format(
                        "Olá %s, o seu corte está agendado para dia %02d/%02d/%d às %02d:%02d",
                        filaObj.peek().getCliente().getNome(),
                        dataHora.getDayOfMonth(),
                        dataHora.getMonthValue(),
                        dataHora.getYear(),
                        dataHora.getHour(),
                        dataHora.getMinute()
                );

                if (email != null && text != null) {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom("barbeariaTM@email.com");
                    message.setTo(email);
                    message.setSubject("Agendamento Barbearia TM");
                    message.setText(text);
                    mailSender.send(message);
                    filaObj.poll();
                } else {

                    System.err.println("Failed to send email: email or text is null");
                }
            }
        }
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
