package school.sptech.naumspringapi.dto.semanaDto;

import lombok.Data;

import java.util.Map;

@Data
public class SemanaCriacaoDto {
    private Map<String,Boolean> segunda;
    private Map<String,Boolean> terca;
    private Map<String,Boolean> quarta;
    private Map<String,Boolean> quinta;
    private Map<String,Boolean> sexta;
    private Map<String,Boolean> sabado;
    private Map<String,Boolean> domingo;
}
