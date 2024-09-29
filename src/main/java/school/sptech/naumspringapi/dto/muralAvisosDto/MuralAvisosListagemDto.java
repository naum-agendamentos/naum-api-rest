package school.sptech.naumspringapi.dto.muralAvisosDto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.TipoAviso;

import java.time.LocalDateTime;

@Data
public class MuralAvisosListagemDto {
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private LocalDateTime data;
    private TipoAviso tipoAviso;
    private Barbeiro barbeiro;
}
