package school.sptech.naumspringapi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataAgendamento;
    @OneToOne
    @JoinColumn(name = "id")
    private Barbeiro barbeiro;
    @OneToOne
    @JoinColumn(name = "id")
    private Cliente cliente;
    @OneToMany
    @JoinColumn(name = "id")
    private List<Servico> servico;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Servico> getServico() { return servico; }

    public void setServico(List<Servico> servico) { this.servico = servico; }
}
