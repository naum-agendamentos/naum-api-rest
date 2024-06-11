package school.sptech.naumspringapi.email;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/emails")
public class EmailController {

    private final EmailService service;
    @GetMapping("/enviar-email-cliente")
    public ResponseEntity<Void> enviarEmail() {
        service.sendEmailDia();
        return ResponseEntity.ok().build();
    }
}
