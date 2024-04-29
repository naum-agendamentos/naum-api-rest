package school.sptech.naumspringapi.entity;

import jakarta.persistence.*;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int qtdEstrela;
    @OneToOne
    @JoinColumn(name = "id")
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name = "id")
    private Barbearia barbearia;

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
