package school.sptech.naumspringapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EntidadeImprocessavelException extends RuntimeException {
    public EntidadeImprocessavelException(String entity) {
        super(String.format("Campos inválidos ou incompletos. Entidade não processada: %s", entity));
    }
}
