package school.sptech.naumspringapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class IndisponivelException extends RuntimeException {
    public IndisponivelException(String entity) {
        super(String.format("%s não disponível", entity));
    }
}
