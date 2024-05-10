package school.sptech.naumspringapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequisicaoInvalidaException extends RuntimeException {
    public RequisicaoInvalidaException(String entity) {
        super(String.format("dados inválidos ou requisição não pode ser processada em %s, 'bad request'", entity));
    }
}
