package school.sptech.naumspringapi.email;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class EmailScheduler {

    private final EmailService emailService;
    @Scheduled(fixedRate = 15000)
    public Void enviarEmail() {
        emailService.sendEmailDia();
        return null;
    }

}
