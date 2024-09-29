package school.sptech.naumspringapi.dto.semanaDto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class SemanaListagemDto {
    private Map<String,Boolean> segunda = new HashMap<String,Boolean>();
    private Map<String,Boolean> terca = new HashMap<String,Boolean>();
    private Map<String,Boolean> quarta = new HashMap<String,Boolean>();
    private Map<String,Boolean> quinta = new HashMap<String,Boolean>();
    private Map<String,Boolean> sexta = new HashMap<String,Boolean>();
    private Map<String,Boolean> sabado = new HashMap<String,Boolean>();
    private Map<String,Boolean> domingo = new HashMap<String,Boolean>();

    public void setSegunda(String dia, Boolean indisponivel) {
        this.segunda.put(dia,indisponivel);
    }

    public void setTerca(String dia, Boolean indisponivel) {
        this.terca.put(dia,indisponivel);
    }

    public void setQuarta(String dia, Boolean indisponivel) {
        this.quarta.put(dia,indisponivel);
    }

    public void setQuinta(String dia, Boolean indisponivel) {
        this.quinta.put(dia,indisponivel);
    }

    public void setSexta(String dia, Boolean indisponivel) {
        this.sexta.put(dia,indisponivel);
    }

    public void setSabado(String dia, Boolean indisponivel) {
        this.sabado.put(dia,indisponivel);
    }

    public void setDomingo(String dia, Boolean indisponivel) {
        this.domingo.put(dia,indisponivel);
    }
}
