package school.sptech.naumspringapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private int qtdEstrela;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdEstrela() {
        return qtdEstrela;
    }

    public void setQtdEstrela(int qtdEstrela) {
        this.qtdEstrela = qtdEstrela;
    }
}
